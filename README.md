# CSCI 205 - Software Engineering and Design
Bucknell University
Lewisburg, PA

### Course Info
Instructor: Lily Romano 
Semester: CSCI 205 Spring 

## Team Information
- Aleena Sultan
  - Computer Science and Engineering
  - 2027
- Khanh Cao
  - Computer Engineering 
  - 2027

## Project Information
Mastermind is a code-breaking game where the player must guess a randomly generated secret code within 12 turns. Each guess is evaluated using red and white scoring pegs:
- A **red scoring peg** is awarded for each code peg that is the correct color and in the correct position.
- A **white scoring peg** is given for each code peg that is the correct color but in the wrong position.
- The scoring pegs do not indicate which specific pegs are correct or incorrect, adding to the challenge.

The game also features different solver algorithms that attempt to break the code using various strategies, such as RandomSolver, StupidSolver, and MinMaxSolver.
The user can customise how many turns they want to run the game for and choose whether they want to play it themselves or run a Solver. 
## How to run it
1. **Compile the project** using a Java compiler:
   ```sh
   javac Main.java
   ```
2. **Run the program** by executing the `Main` class:
   ```sh
   java Main
   ```
3. **Choose your mode**:
   - **Game Mode**: Play manually by guessing the secret code within 12 turns.
   - **Solver Mode**: Let one of the three automated solvers play the game:
     - **RandomSolver**: Makes random guesses.
     - **StupidSolver**: Uses a naive strategy to guess.
     - **MinMaxSolver**: Implements a more strategic approach to solving the code.
4. **Follow the on-screen prompts** to enter your choices and interact with the game.

