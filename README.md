# Portfolio Rebalancing Module

As outlined in the challenge's original README.md, this program takes a (possibly) unbalanced portfolio, and rebalances it such that each investment meets its given target allocation. In addition, the program also validates the unbalanced portfolio input prior to processing.

## Functionality

The program takes a `*.csv` file which represents a user's portfolio. It then runs the algorithm on the portfolio, and prints a set of buy/sells needed for the portfolio to be balanced to the console. It also creates a new and balanced portfolio based on the set of buy/sells needed.

### How to run

To use the program:

1. Edit the file `input.csv`, located in the Eclipse project's root directory.
2. Each line in the file is an investment, and should be in the following format: `ticker, target_allocation, actual_allocation, shares_owned, share_price`.
3. Run the `PortfolioRebalance` class.`
 
### Algorithm

## Technical choices

## Testing

Unit testing was done with JUnit 4 unit-test framework. The project's test code mostly cover the following issues:

* Input validation
* Output validation
* Whether the balanced portfolio's allocations are within acceptable range of the target allocations.

### How to test

1. Add test input `*.csv` files into `$PROJECT_ROOT/test_inputs`:
 * For valid test inputs, name the file in the following format: `test_input_valid_#` where `#` is the test number.
 * For invalid test inputs, name the file in the following format: `test_input_invalid_#` where `#` is the test number.

2. Run the appropriate test classes:
 * For all test cases, run the `TestSuite` class.
 * For input validation test cases, run the `TestInputValidationAccept` class.
 * For input invalidation test cases, run the `TestInputValidationReject` class.
 * For rebalancing test cases, run the class `TestRebalance` class.
