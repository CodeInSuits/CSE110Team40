# -*- coding: utf-8 -*-

__author__ = 'Dan'
__email__ = 'daz040@eng.ucsd.edu'

from assignment2 import Player


class IDMPlayer(Player):
    """
    Basic Approach, iterative deepening minimax player.
    And the end of each depth limit or when time_up flag is raised
    Use the heurestic function from part 3 to evaluate current state.
    Also use transposition table for each explored state to speed up time
    """
    def __init__(self):
        """Called when the Player object is initialized. You can use this to
        store any persistent data you want to store for the  game.

        For technical people: make sure the objects are picklable. Otherwise
        it won't work under time limit.
        """
        self.TT={} # initialize empty transposition table
        self.bestAction = None# storing best action
        self.bestFlag = None # flag if terminal answer has found
        self.maxDepth = 1 # max depth
        self.EVAL = True # if evaluation function is used
        

    def Min(self, state, alpha, beta, depth):
        """
        Depth limited version of alpha-beta prunign minimax Min
        Param: State, current state
                Alpha, current state's current alpha (lower bound for max)
                Beta, current state's current beta (upper bound for min)
                Depth, current depth for depth limit
        Return: Utility, best utility value of current state
                    or worst utility value for our player
        """
        # Similar to Min() in part 1, except using alpha-beta pruning
        # and a transposition table to prune braches that are already bad

        # first check if state has been saved in transposition table
        if state.ser() in self.TT:
            util,depthX = self.TT[state.ser()]
            # use stored value if the number of expansion depth left
            # is not greater than stored one
            if (depthX >= self.maxDepth - depth):
                return util

        
        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # calling utility function with self will always
            # check winning status of our player
            util = state.utility(self)
            self.TT[state.ser()] = (util,self.maxDepth-depth)
            return util

        # if reaching current max depth, return utility
        # with evaluation function
        if depth == self.maxDepth:
            self.EVAl = True
            util = self.evaluate(state,self.row)
            self.TT[state.ser()] = (util,self.maxDepth-depth)
            return util
        
        # Above functions runs pretty fast
        # So we don't need to worry about time check
        if self.is_time_up():
            # Use transposition value if exist
            # otherwise use evaluation function
            if state.ser() in self.TT:
                util,depthX = self.TT[state.ser()]
            else:
                # Because time is up so EVAL flag shouldn't matter
                # This is just for safety check
                self.EVAl = True
                util = self.evaluate(state,self.row)
                self.TT[state.ser()] = (util,0)
            return util
        
        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Min, then call Max again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Max again
            # result function with None action simply returns
            # the same state with opponent
            util = self.Max(state.result(None),alpha,beta, depth+1)
            self.TT[state.ser()] = (util,self.maxDepth-depth)
            return util

        # our opponent wants worst utility for our player
        currentScore = 1
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Max(newBoard, alpha, beta, depth+1) # +1 depth for next pair of turns 
            if(alpha > newScore): # alpha pruning
                return (newScore,self.maxDepth)
            if(currentScore > newScore):
                currentScore = newScore
            # update beta if currentScore is smaller
            beta = currentScore if currentScore < beta else beta

        # only save the non-pruned result to transposition table
        # because pruned states might have states cause different result
        self.TT[state.ser()] = (currentScore, self.maxDepth-depth)
        return currentScore
                
    def Max(self, state, alpha, beta, depth):
        """
        Depth limited version of alpha-beta prunign minimax Max
        Param: State, current state
                Alpha, current state's current alpha (lower bound for max)
                Beta, current state's current beta (upper bound for min)
        Return: Utility, best utility value of current state as well as our player
        """
        # Similar to Max() in part 1, except using alpha-beta pruning
        # and a transposition table to prune braches that are already bad

        # first check if state has been saved in transposition table
        if state.ser() in self.TT:
            util,depthX = self.TT[state.ser()]
            # use stored value if the number of expansion depth left
            # is not greater than stored one
            if (depthX >= self.maxDepth - depth):
                return util

        
        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # calling utility function with self will always
            # check winning status of our player
            util = state.utility(self)
            self.TT[state.ser()] = (util,self.maxDepth-depth)
            return util

        # max depth is only handled in Min()
        # becuase we define each depth to be a full Min/Max turn

        
        # Above functions runs pretty fast
        # So we don't need to worry about time check
        if self.is_time_up():
            # Use transposition value if exist
            # otherwise use evaluation function
            if state.ser() in self.TT:
                util,depthX = self.TT[state.ser()]
            else:
                # Because time is up so EVAL flag shouldn't matter
                # This is just for safety check
                self.EVAl = True
                util = self.evaluate(state,self.row)
                self.TT[state.ser()] = (util,0)
            return util
       
        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Max, then call Min again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Min again
            # result function with None action simply returns
            # the same state with opponent
            util = self.Min(state.result(None),alpha,beta, depth)
            self.TT[state.ser()] = (util,self.maxDepth - depth)
            return util

        # maximizing the utility
        currentScore = -1
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore = self.Min(newBoard, alpha, beta, depth) 
            if(beta < newScore): # beta pruning
                return (newScore,flag)
            if(currentScore < newScore):
                currentScore = newScore
            # update alpha if currentScore is larger
            alpha = currentScore if currentScore > alpha else alpha

        # only save the non-pruned result to transposition table
        # because pruned states might have states cause different result
        self.TT[state.ser()] = (currentScore,self.maxDepth-depth)
        return currentScore

    def evaluate(self, state, my_row):
        """
        Evaluate our player's utility using heuristic function
        This heuristic function returns value in [-1,1]
        so it also works well with alpha-beta pruning
        Para: current state
        return: utility value for our player
        """

        # get player/opponent's goal/pit stones number
        player_goal_stones = state.board[state.player_goal_idx]
        opponent_goal_stones = state.board[state.opponent_goal_idx]
        player_pit_stones=0
        for i in xrange(*state.possible_action_range()):
            player_pit_stones += state.board[i]
        total = 2*state.M*state.N
        opponent_pit_stones= total - player_goal_stones-opponent_goal_stones-player_pit_stones
        prefactor = 1.0/total
        
        # check if we are in opponent's turn
        if my_row == state.player_row:
            weight = (player_goal_stones-opponent_goal_stones+player_pit_stones-opponent_pit_stones)
        else:
            # minus sign for being in opponent's turn
            weight = -(player_goal_stones-opponent_goal_stones+player_pit_stones-opponent_pit_stones)
        return prefactor * weight
    
    
    def move(self, state):
        """
        while time_up is not up, using iterative deepening minimax with alpha-beta
        algorithm to find best move
        If time_up is called, return the best action found so with highest depth
        In our definition, one depth is one full round of turn
        (i.e. each player play their turn exactly once if possible)
        :param state: State, the current state of the board.
        :return: Action, the next move
        """
        possibleMoves = state.actions()
        if(len(possibleMoves) == 0):
            return None
        self.bestAction = possibleMoves[0]
        self.maxDepth = 1
        self.EVAL = True # if evaluaete() is called
        while (not self.is_time_up() and self.EVAL):
        #while (self.maxDepth < 15 and not self.is_time_up()):
            bestUtil = -1
            action = possibleMoves[0] # default action
            # running minimax algorithm with alpha-beta prunign to find best move
            # return action with max min_utility
            alpha = -2 # act like -inf
            beta = 2 # act like inf
            for nextMove in possibleMoves:
                # if time is up, return current bestAction
                if (self.is_time_up()):
                    print "time status:",self.is_time_up()
                    return self.bestAction
                newBoard = state.result(nextMove)
                newScore = self.Min(newBoard, alpha, beta, 0) # We are maximizing the min here
                if bestUtil < newScore:
                    bestUtil = newScore
                    action = nextMove
                    # update alpha since we are doing maximization
                    alpha = newScore
            self.bestAction = action
                
            self.maxDepth += 1
        # in case break out of while loop
        print "break out of while:",self.maxDepth,"time:",self.is_time_up()
        return self.bestAction
