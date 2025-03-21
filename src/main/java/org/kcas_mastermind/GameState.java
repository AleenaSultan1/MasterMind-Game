/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:40 am
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
 * Class: GameState
 *
 * Description:
 *
 * ****************************************
 */

package org.kcas_mastermind;
public enum GameState {
    IN_PROGRESS {
        @Override
        public boolean canTransitionTo(GameState nextState) {

            return nextState == END;
        }
    },

    END{
        @Override
        public boolean canTransitionTo(GameState nextState){
            return false;
        }
    };
    public abstract boolean canTransitionTo(GameState nextState);
}
