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