# PolicyAggregator
Repository for policy aggregator application

Aggregator database will have the below format

[

{
id: 1,
providerName: "HDFC",
providerUrl: "http://localhost:4040/api/v1/insuranceProviders",
responseType: "{"planId": "planId","planName" : "planName","providerName": "insuranceProviderName","planCoverage": "insuranceAmount"}",
responseFormat: "JSON"
},

{
id: 2,
providerName: "ICICI",
providerUrl: "http://localhost:2020/api/v1/insurance",
responseType: "{"planId": "id","planName" : "plan_name","providerName": "insuranceCompany","planCoverage": "annualPremium"}",
responseFormat: "JSON"
}

]

