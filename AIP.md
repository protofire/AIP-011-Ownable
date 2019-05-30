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
TBD 

Java rewrite of https://github.com/OpenZeppelin/openzeppelin-solidity/blob/master/contracts/ownership/Ownable.sol
<!-- *  One sentence summary of the proposal -->

## Value Proposition 
TBD
<!-- *  Brief summary of what value this improvement provides to the overall Aion community. -->

## Motivation
TBD
<!-- * Explain the motivation for submitting this proposal. Who does it benefit and why is it needed? Provide any relevant data or research. -->

## Non-Goals
TBD
<!-- * Provide a brief explanation of what is out-of-scope for this proposal and what it is not designed to achieve. -->

## Success Metrics
TBD 
<!-- * If applicable, what metrics and benchmarks can be tracked to evaluate the success of this proposal. -->

## Description
TBD
<!-- *  Summary of the context and proposed improvement. -->
 
## Specification
TBD
<!-- * Technical specification of the proposed improvement containing the appropriate syntax and semantics. Should cover the various active implementations of Aion. -->

## Logic
TBD
<!-- * Walk-through of the design and specification considerations, choices and approach. Provide practical examples of similar implementations, feedback from the community and perspectives on possible concerns. -->

## Risks & Assumptions
TBD
<!-- * Breakdown potential risks introduced by this proposal. How does it affect compatibility? Are there any internal or external events that could threaten the effectiveness of this proposal? Provide mitigation strategies for all listed risks and identify any assumptions. -->

## Test Cases
TBD
<!-- * Provide test cases for the improvements implementation. Test cases are essential in “Core” improvement proposals. -->

## Implementations

### Definitions
TBD
<!-- * Proposed code to be implemented. This section must be complete and adopted for the AIP to be set to “Final”. Implementations must cover the majority of active Aion clients. -->

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
TBD 
<!-- * Identify any other AIP's, modules, libraries, or API's that are dependencies for this AIP to be implemented or achieve its value proposition. -->

## Copyright
 All AIP’s are public domain. Copyright waiver to be linked 
 to https://creativecommons.org/publicdomain/zero/1.0/
