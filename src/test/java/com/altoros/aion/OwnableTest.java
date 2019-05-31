package com.altoros.aion;

import avm.Address;
import org.aion.avm.core.util.ABIUtil;
import org.aion.avm.tooling.AvmRule;
import org.aion.vm.api.interfaces.IExecutionLog;
import org.aion.vm.api.interfaces.ResultCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.altoros.aion.Ownable.Const.OWNERSHIP_TRANSFERRED;

public class OwnableTest {
    @ClassRule
    public static AvmRule avmRule = new AvmRule(true);

    //default address with balance
    private static Address owner = avmRule.getPreminedAccount();
    private static Address other = avmRule.getRandomAddress(BigInteger.valueOf(100_000_000));

    private Address dappAddr;

    @Before
    public void deployDapp() {
        //deploy Dapp:
        // 1- get the Dapp byes to be used for the deploy transaction
        // 2- deploy the Dapp and get the address.
        byte[] dapp = avmRule.getDappBytes(Ownable.class, null);
        AvmRule.ResultWrapper deployed = avmRule.deploy(owner, BigInteger.ZERO, dapp);
        Assert.assertNotNull("Failed to deploy app", deployed);
        dappAddr = deployed.getDappAddress();
    }

    private Object doCall(String methodName, Object... arguments) {
        AvmRule.ResultWrapper result = doCall(owner, methodName, arguments);

        // getReceiptStatus() checks the status of the transaction execution
        ResultCode status = result.getReceiptStatus();
        System.out.println(status.name());

        Assert.assertTrue(status.isSuccess());
        List<IExecutionLog> logs = result.getLogs();
        for (IExecutionLog l : logs) {
            String str = new String(l.getData());
            System.out.println(str);
        }
        return result.getDecodedReturnData();
    }

    private AvmRule.ResultWrapper doCall(Address from, String methodName, Object... arguments) {
        //calling Dapps:
        // 1- encode method name and arguments
        // 2- make the call;
        byte[] txData = ABIUtil.encodeMethodArguments(methodName, arguments);
        AvmRule.ResultWrapper result = avmRule.call(from, dappAddr, BigInteger.ZERO, txData);
        return result;
    }

    @Test
    public void owner() {
        Object address = doCall("getOwner");
        Assert.assertEquals(address, owner);
    }

    @Test
    public void renounceOwnership_whenNotOwnerCalling_success() { //onlyOwner
        AvmRule.ResultWrapper result = doCall(owner, "renounceOwnership");
        Assert.assertTrue(result.getReceiptStatus().isSuccess());

        Optional<IExecutionLog> event = findAnyLog(result, OWNERSHIP_TRANSFERRED);
        Assert.assertTrue("Event is not emitted", event.isPresent());
    }

    @Test
    public void renounceOwnership_whenNotOwnerCalling_returnError() { //onlyOwner
        AvmRule.ResultWrapper result = doCall(other, "renounceOwnership");
        Assert.assertTrue(result.getReceiptStatus().isFailed());

        Optional<IExecutionLog> event = findAnyLog(result, OWNERSHIP_TRANSFERRED);
        Assert.assertFalse("Event is emitted", event.isPresent());
    }

    @Test
    public void transferOwnership_byOwner_succeed() { //onlyOwner
        doCall("transferOwnership", other);
        Object currentOwner = doCall("getOwner");
        Assert.assertEquals(other, currentOwner);
    }

    @Test
    public void transferOwnership_byNotOwner_rejected() { //onlyOwner
        doCall(other,"transferOwnership", other);
        Object currentOwner = doCall("getOwner");
        Assert.assertEquals(owner, currentOwner);
    }

    private Optional<IExecutionLog> findAnyLog(AvmRule.ResultWrapper result, String event) {
        for (IExecutionLog log : result.getLogs()) {
            if (!log.getTopics().isEmpty()) {
                String topic = new String(log.getTopics().get(0)).trim();
                if (Objects.equals(event, topic)) {
                    return Optional.of(log);
                }
            }
        }
        return Optional.empty();
    }
}

