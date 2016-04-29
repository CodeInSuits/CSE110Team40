# -*- coding: utf-8 -*-
__author__ = 'Lingyi Kong'
__email__ = 'l1kong@ucsd.edu'

from assignment2 import Player, State, Action

class AlphaBetaPlayer(Player):
    def __init__(self):
        self.TT={} # initialize empty transposition table
    def Min(self, state, alpha, beta):
        """
        Param: State, current state
                Alpha, current state's current alpha (lower bound for max)
                Beta, current state's current beta (upper bound for min)
        Return: Utility, best utility value of current state
                    or worst utility value for our player
        """
        # Similar to Min() in part 1, except using alpha-beta pruning
        # and a transposition table to prune braches that are already bad

        # first check if state has been saved in transposition table
        if state.ser() in self.TT:
            return self.TT[state.ser()]
        
        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # calling utility function with self will always
            # check winning status of current player
            # because in terminal status, the utility is either -1 or 1
            # which is either the highest or lowest value
            # only to compare two cases
            util = state.utility(self)
            self.TT[state.ser()] = util
            return util
        
        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Min, then call Max again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Max again
            # result function with None action simply returns
            # the same state with opponent
            util = self.Max(state.result(None),alpha,beta)
            self.TT[state.ser()] = util
            return util

        # our opponent wants worst utility for our player
        currentScore = 1
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Max(newBoard, alpha, beta) 
            if(alpha > newScore): # alpha pruning
                return newScore            
            if(currentScore > newScore):
                currentScore = newScore
            # update beta if currentScore is smaller
            beta = currentScore if currentScore < beta else beta

        # only save the non-pruned result to transposition table
        # because pruned states might have states cause different result
        self.TT[state.ser()] = currentScore
        return currentScore
                
    def Max(self, state, alpha, beta):
        """
        Param: State, current state
                Alpha, current state's current alpha (lower bound for max)
                Beta, current state's current beta (upper bound for min)
        Return: Utility, best utility value of current state as well as our player
        """
        # Similar to Max() in part 1, except using alpha-beta pruning
        # and a transposition table to prune braches that are already bad

        # first check if state has been saved in transposition table
        if state.ser() in self.TT:
            return self.TT[state.ser()]
        
        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # calling utility function with self will always
            # check winning status of current player
            # because in terminal status, the utility is either -1 or 1
            # which is either the highest or lowest value
            # only to compare two cases
            util = state.utility(self)
            self.TT[state.ser()] = util
            return util
        
        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Max, then call Min again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Min again
            # result function with None action simply returns
            # the same state with opponent
            util = self.Min(state.result(None),alpha,beta)
            self.TT[state.ser()] = util
            return util

        # maximizing the utility
        currentScore = -1
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Min(newBoard, alpha, beta) 
            if(beta < newScore): # beta pruning
                return newScore            
            if(currentScore < newScore):
                currentScore = newScore
            # update alpha if currentScore is larger
            alpha = currentScore if currentScore > alpha else alpha

        # only save the non-pruned result to transposition table
        # because pruned states might have states cause different result
        self.TT[state.ser()] = currentScore
        return currentScore
    
    def move(self, state):
        """Calculates the best move from the given board using the minimax
        algorithm with alpha-beta pruning and transposition table.
        :param state: State, the current state of the board.
        :return: Action, the next move
        """

        action = None
        possibleMoves = state.actions()
        if(len(possibleMoves) == 0):
            return action
        bestUtil = -1
        action = possibleMoves[0] # default action
        # running minimax algorithm with alpha-beta prunign to find best move
        # return action with max min_utility
        alpha = -2 # act like -inf
        beta = 2 # act like inf
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Min(newBoard, alpha, beta) # We are maximizing the min here
            if bestUtil < newScore:
                bestUtil = newScore
                action = nextMove
                # update alpha since we are doing maximization
                alpha = newScore
                
        return action
        
        raise NotImplementedError("Need to implement this method")
