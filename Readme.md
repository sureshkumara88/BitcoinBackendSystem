# Bitcoin Backend System

This is a backend system for retrieving the bitcoin price movements for certain period and trade decision based on EMA calculation. 

## Getting Started

● Allowing users to see the bitcoin price movement for last week, last month, last year or any
custom date.

● Allowing users to see the X days rolling / moving average bitcoin prices between two custom
dates.

● Allowing users to get bitcoin trading decision on whether to BUY, SELL or HOLD based on the
price movement for the last X days (use any calculation/algorithm which you’d consider optimal to
determine the outcome).

● To get the historic data on bitcoin prices, system using the following API exposed by coinbase:
https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=all

### Prerequisites

Need maven repository in your local and download all the dependencies that are specified in pom.xml file.

### Installing

Once you imported the system, you need to compile and install the project into your local maven repository. And, then you can add this artifact as a dependency for your maven project.

		<dependency>
			<groupId>com.bitcoin.price.movements</groupId>
			<artifactId>BitcoinSystem</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

## Running the tests
		
you need to call the below services using PriceMovementService object, which will return with list of Data object, for getting price movements for specific period,

		getDataForLastWeek();
		getDataForLastMonth();
		getDataForLastYear();
		getDataForDates(Date fromDate, Date toDate);
		
you need to call the below service using TradingDecisionService object, which will return with String object, for trade decision based on EMA calculation,

		makeTradeDecision(Integer days);

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Author

Suresh Kumar - [BitcoinSystem](https://github.com/sureshkumara88/BitcoinBackendSystem.git)

