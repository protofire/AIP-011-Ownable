## Ownable

---
Title: Ownable Smart Contract
Author(s): Manuel Garcia, Pavel Orda
Type: ASC (Aion Standards and Conventions)
Status: DRAFT
Creation Date: May 28th, 2019
Contact Information: manuel.garcia@altoros.com

---

## Summary
Contract module for simple authorization and access control mechanisms. 

## Value Proposition 
Contract module which provides a basic access control mechanism, where there is an account (an owner) that can be granted exclusive access to specific functions.

## Motivation
This module could be used through inheritance. It will make available the modifier `onlyOwner`, which can be aplied to your functions to restrict their use to the owner.

## Non-Goals

## Success Metrics

## Description
<!-- *  Summary of the context and proposed improvement. -->
 
## Specification
Based on Solidity implementation https://github.com/OpenZeppelin/openzeppelin-solidity/blob/master/contracts/ownership/Ownable.sol

## Logic
<!-- * Walk-through of the design and specification considerations, choices and approach. Provide practical examples of similar implementations, feedback from the community and perspectives on possible concerns. -->

## Risks & Assumptions

## Test Cases
[Test cases reference implementation](https://github.com/protofire/AIP-011-Ownable/blob/master/src/test/java/com/altoros/aion/OwnableTest.java)
## Implementations

### Definitions
[Reference implementation](https://github.com/protofire/AIP-011-Ownable/blob/master/src/main/java/com/altoros/aion/Ownable.java)

### Methods

**`getOwner` function**

Returns the address of the current owner.

> **returns:** Address of the owner

``` java
public static Address getOwner()
```

**`renounceOwnership` function**

Leaves the contract without owner.  Can only be called by the current owner.

> **Note:** Renouncing ownership will leave the contract without an owner,
thereby removing any functionality that is only available to the owner.


``` java
public static void renounceOwnership()
```

**`transferOwnership` function**

Transfers ownership of the contract to a new account (`newOwner`).
Can only be called by the current owner.

``` java
public static void transferOwnership(Address newOwner)
```
> **parameters**  
> `tokenHolder`: Address for which the balance is returned.


**`onlyOwner` function**

Stops transaction if called by any account other than the owner.
Do not work for function calls

``` java
private static void onlyOwner()
```


**`OwnershipTransferred` event** <a id="revokedoperator"></a>

Indicates ownership change form `oldOwner` address to `newOwner` address.

> **parameters**  
> `oldOwner`: Address of previous owner.  
> `newOwner`: Address of new owner.

``` java
private static void emitOwnershipTransferredEvent(Address oldOwner, Address newOwner)
```

## Dependencies 
<!-- * Identify any other AIP's, modules, libraries, or API's that are dependencies for this AIP to be implemented or achieve its value proposition. -->

## Copyright
 All AIP’s are public domain. Copyright waiver to be linked 
 to https://creativecommons.org/publicdomain/zero/1.0/
