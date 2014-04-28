# Portfolio Rebalancing Module

As outlined in the challenge's original README.md, this program takes a (possibly) unbalanced portfolio, and rebalances it such that each investment meets its given target allocation. 

## Functionality

The program takes a `*.csv` file which represents a user's portfolio. It then runs the algorithm on the portfolio, and prints a set of buy/sells needed for the portfolio to be balanced to the console. It also creates a new and balanced portfolio based on the set of buy/sells needed.

In addition, the program also validates the unbalanced portfolio input prior to processing, as well as the balanced output portfolio.

### How to run

To use the program:

1. Edit the file `input.csv`, located in the Eclipse project's root directory.
2. Each line in the file is an investment, and should be in the following format:

 `ticker, target_allocation, actual_allocation, shares_owned, share_price`.
 
3. Run the `PortfolioRebalance` class.`

## Technical choices

The following design decisions were made:

* Use of `double` primitives instead of `BigDecimal` for decimal numbers.

 This is mainly because `BigDecimal`s may take a toll on performance, program scalability, and code complexity.git

* Use of approximate comparisons of decimal numbers, e.g. rounding of decimal numbers (to 2 decimal places by default) before doing comparisons on them.

 This is again partly to avoid the use of `BigDecimal` in calculations, while at the same time still a reliable and simple alternative.

## Testing

Unit testing was done with JUnit 4 unit-test framework. The project's test code mostly cover the following issues:

* Input/output validation
* Output acceptability

### How to test

To run tests:

1. Add test input `*.csv` files into `$PROJECT_ROOT/test_inputs`:
 * For valid test inputs, name the file in the following format: `test_input_valid_#` where `#` is the test number.
 * For invalid test inputs, name the file in the following format: `test_input_invalid_#` where `#` is the test number.

2. Run the appropriate test classes:
 * For all test cases, run the `TestSuite` class.
 * For input validation test cases, run the `TestInputValidationAccept` class.
 * For input invalidation test cases, run the `TestInputValidationReject` class.
 * For rebalancing test cases, run the class `TestRebalance` class.

### Testing choices

#### Input/output validation

Input/output validation was done to ensure inputs' data are reliable before processing them, and that the outputs are correct (which does not cover their acceptability). The tests mostly cover the following points:

* Ensure all given target and actual allocation values add up to 100%
* Ensure each investment's given actual allocation is correct, calculated based on each given share price, share count, and total investment.

By covering the aforementioned points, the program can safely assume that its inputs/outputs have reliable and mathematically correct information.

#### Output acceptability

Output acceptability here is defined as whether or not the balanced portfolio outputs' actual allocations are within acceptable range of the target allocations. To ensure high acceptability of outputs, the tests cover the following points:

* Ensure that the absolute errors between the output portfolio's investments' target and actual allocations are all within a constant and customizable sensitivity range (default is 2%). 

Hence the program's acceptability be guaranteed to be within a sensitivity range, which can be easily adjusted based on the users' needs.
