#### Deployment

> **Requires**: Java 10+, Maven 2.5.4+ 

To build and deploy contract run:
```bash
mvn initialize
mvn clean package
mvn aion4j:deploy -Premote -Dpk=<you aion pk>
```

To cache deployed contract address as current use:

```bash
mvn aion4j:get-receipt -Dtail -Dsilent -Premote
```

#### Usage

Method call:
```bash
mvn aion4j:call --Dmethod=<method> -Dargs="<method args>" -Premote 
```

Transaction:
```bash
mvn aion4j:contract-txn -Dmethod=<method> -Dargs="<method args>" -Premote -Dpk=<you aion pk>
```