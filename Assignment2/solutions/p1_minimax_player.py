# -*- coding: utf-8 -*-
__author__ = 'Utkrisht Rajkumar'
__email__ = 'urajkuma@ucsd.edu'

import sys

sys.path.append("../src")
from assignment2 import Player, State, Action

class MinimaxPlayer(Player):
    def __init__(self):
        self.cache ={}
    
    def Min(self, state):
        """ 
        Args: Takes in state
        returns: utility for OUR player and best action for opponent
        """

        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # utility function returns value for opponent
            # thus we take the opposite value for our player
            return -state.utility(state.player), None

        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Min, then call Max again on that same state
        if(len(possibleMoves) == 0):
			# need to switch opponent when calling Max again
			# result function with None action simply returns
			# the same state with opponent
            return self.Max(state.result(None))

        # Our opponent wants to have -1 for our player's utility
        currentScore = 1 # assuming losing position for our opponent
        bestAction = possibleMoves[0]

        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore, tmp = self.Max(newBoard)
            if(currentScore > newScore):
                currentScore = newScore
                bestAction = nextMove
        return currentScore, bestAction


    def Max(self, state):
        """ Args: Takes in state
        returns: Utility for our player and best action for our player
        """
        if(state.is_terminal()):
            return state.utility(state.player), None

        possibleMoves = state.actions()

        #if no possible moves for Max, then call Min again on that same state
        if(len(possibleMoves) == 0):
			# need to switch opponent when calling Max again
			# result function with None action simply returns
			# the same state with opponent
            return self.Min(state.result(None))

        currentScore = -1 # Initialize losing position
        bestAction = possibleMoves[0] # If no better option, take first one

        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore, tmp = self.Min(newBoard)
            if(currentScore < newScore):
                currentScore = newScore
                bestAction = nextMove

                
        return currentScore, bestAction

    def move(self, state):
        """3
        Calculates the best move from the given board using the minimax
        algorithm.
        :param state: State, the current state of the board.
        :return: Action, the next move
        """
        action = None
        possibleMoves = state.actions()
        if(len(possibleMoves) == 0):
            return action
        bestUtil = -1
        bestAction = 0
        #get the inital actions and states from given state
        for player, idx in enumerate(possibleMoves):
            newBoard = state.result(idx)
        """
                Based on which player's turn it is, call MAX or MIN.
                This will iterate through the tree and run the minimax
                algorithm. Then, when the MAX or MIN return all the way
                back to the move function, check if the util of that 
                state is a win or lose. Do that for all the initial states
                and keep track which action will result in a win. Then call
                Action on that action.
                
            if(state.player_row == 0):
                currentUtil = self.Max(newBoard)
            else:
                currentUtil = self.Min(newBoard)

            #update which of the initial actions give the best result
            if(bestUtil < currentUtil):
                bestUtil = currentUtil
                bestAction = idx

        action = Action(state.player_row, bestAction)
        """

        # Call Max to find best action
        tmp, action = self.Max(state)
        return action 

        raise NotImplementedError("Need to implement this method")
