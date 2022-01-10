Feature:
  This API is for external services to retrieve the latest quantity that aCommerce allocated stock to the Marketplace


  # 1.1 Create Test Script to testing this API Endpoint at least 10 test cases
  Scenario: Verify response status code if pass non-existed channel id to url
    Given I perform GET operation for "/channel/channel0100/allocation/merchant/partner0001?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if pass wrong channel id format to url
    Given I perform GET operation for "/channel/abcd/allocation/merchant/partner0001?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if pass non-existed partner id to url
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner1001?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if pass wrong partner id format to url
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/abcd123?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if pass non-existed channel id and non-existed partner id to url
    Given I perform GET operation for "/channel/channel0101/allocation/merchant/partner1001?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if pass wrong channel id and wrong partner id to url
    Given I perform GET operation for "/channel/abcd/allocation/merchant/abcd123?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if pass wrong page number
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner0001?page=1000&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if do not pass page size
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner0001?page=1&page_size=" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if do not pass page number and page size
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner0001?page=&page_size=" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if do not pass page number
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner0001?page=&page_size=100" with token "tokenId"
    Then I should see response status code as "404"

  Scenario: Verify response status code if pass wrong token id
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner0001?page=1&page_size=100" with token "wrongTokenId"
    Then I should see response status code as "403"

  Scenario: Verify response status code if send request successfully
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/abcd123?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "200"

  Scenario: Verify response status code if use wrong api method
    Given I perform POST operation for "/channel/channel0001/allocation/merchant/abcd123?page=1&page_size=100" with token "tokenId"
    Then I should see response status code as "405"

  Scenario: Verify number of quantity of page
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner0001?page=1&page_size=100" with token "tokenId"
    Then I should see number of quantity less than or equal to "100"

  # 1.2 Create Test Script loop to find "SKU0005" and print the value in log console or a file with the format
  Scenario: Verify SKU005 in Product List
    Given I perform GET operation for "/channel/channel0001/allocation/merchant/partner0001?page=1&page_size=100" with token "tokenId"
    Then I should see "SKU0005" in product list