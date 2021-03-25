$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/cimb/features/MY_CIMB_Deals.feature");
formatter.feature({
  "line": 1,
  "name": "verify CIMB Malaysia Deals",
  "description": "",
  "id": "verify-cimb-malaysia-deals",
  "keyword": "Feature"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "I’m on CIMB MY page",
  "keyword": "Given "
});
formatter.match({
  "location": "CIMB_MY_Deals_Steps.iMOnCIMBMYPage()"
});
formatter.result({
  "duration": 130629000,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Verify Travel \u0026 Fun Deals",
  "description": "",
  "id": "verify-cimb-malaysia-deals;verify-travel-\u0026-fun-deals",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "I select CIMB Deals",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "I would like to explore more for Rentalcars.com under Travel \u0026 Fun",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I will be able to see its details and other similar deals",
  "keyword": "Then "
});
formatter.match({
  "location": "CIMB_MY_Deals_Steps.iSelectCIMBDeals()"
});
formatter.result({
  "duration": 105400,
  "status": "passed"
});
formatter.match({
  "location": "CIMB_MY_Deals_Steps.iWouldLikeToExploreMoreForRentalcarsComUnderTravelFun()"
});
formatter.result({
  "duration": 119600,
  "status": "passed"
});
formatter.match({
  "location": "CIMB_MY_Deals_Steps.iWillBeAbleToSeeItsDetailsAndOtherSimilarDeals()"
});
formatter.result({
  "duration": 115200,
  "status": "passed"
});
});