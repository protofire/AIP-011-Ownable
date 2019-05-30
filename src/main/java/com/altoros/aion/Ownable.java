package com.altoros.aion;

import avm.Address;
import avm.Blockchain;
import org.aion.avm.tooling.abi.Callable;

import static com.altoros.aion.Ownable.Const.NO_ADDRESS;
import static com.altoros.aion.Ownable.Const.OWNERSHIP_TRANSFERRED;

public class Ownable {
    public static class Const {
        public static final Address NO_ADDRESS = new Address(new byte[32]);
        public static final byte[] OWNERSHIP_TRANSFERRED = "OwnershipTransferred".getBytes();
    }

    private static Address owner = NO_ADDRESS;

    /*
     * Initializes the contract setting the deployer as the initial owner.
     */
    static  {
        Address caller = Blockchain.getCaller();
        setOwner(caller);
    }

    /**
     * Returns the address of the current owner.
     */
    @Callable
    public static Address getOwner() {
        return owner;
    }

    private static void setOwner(Address newOwner) {
        emitOwnershipTransferredEvent(owner, newOwner);
        owner = newOwner;
    }

    /**
     * Throws if called by any account other than the owner.
     */
    private static void onlyOwner() {
        Blockchain.require(isOwner());
    }

    private static boolean isOwner() {
        Address caller = Blockchain.getCaller();
        return (owner == caller) || (owner != null && owner.equals(caller));
    }

    /**
     * Leaves the contract without owner. It will not be possible to call
     * `onlyOwner` functions anymore. Can only be called by the current owner.
     * <p>
     * > Note: Renouncing ownership will leave the contract without an owner,
     * thereby removing any functionality that is only available to the owner.
     */
    @Callable
    public static void renounceOwnership() {
        onlyOwner();
        setOwner(NO_ADDRESS);
    }

    /**
     * Transfers ownership of the contract to a new account (`newOwner`).
     * Can only be called by the current owner.
     */
    @Callable
    public static void transferOwnership(Address newOwner) {
        onlyOwner();
        Blockchain.require(!newOwner.equals(NO_ADDRESS));
        setOwner(newOwner);
    }

    private static void emitOwnershipTransferredEvent(Address oldOwner, Address newOwner) {
        Blockchain.log(
                oldOwner.toByteArray(),
                newOwner.toByteArray(),
                OWNERSHIP_TRANSFERRED);
    }
}