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
        

    def Min(self, state, alpha, beta, depth):
        """
        Depth limited version of alpha-beta prunign minimax Min
        Param: State, current state
                Alpha, current state's current alpha (lower bound for max)
                Beta, current state's current beta (upper bound for min)
                Depth, current depth for depth limit
        Return: Utility, best utility value of current state
                    or worst utility value for our player
                Terminal state, if game ends here
        """
        # Similar to Min() in part 1, except using alpha-beta pruning
        # and a transposition table to prune braches that are already bad

        # first check if state has been saved in transposition table
        if state.ser() in self.TT:
            util,flag = self.TT[state.ser()]
            if (flag):
                return self.TT[state.ser()]

        
        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # calling utility function with self will always
            # check winning status of current player
            # because in terminal status, the utility is either -1 or 1
            # which is either the highest or lowest value
            # only to compare two cases
            util = state.utility(self)
            self.TT[state.ser()] = (util,True)
            return (util, True)

        # if reaching current max depth, return utility
        # with evaluation function
        if depth == self.maxDepth:
            return (self.evaluate(state),False)
        
        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Min, then call Max again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Max again
            # result function with None action simply returns
            # the same state with opponent
            util,flag = self.Max(state.result(None),alpha,beta, depth+1)
            self.TT[state.ser()] = (util,flag)
            return (util, flag)

        # our opponent wants worst utility for our player
        currentScore = 1
        currentFlag = None
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore, flag = self.Max(newBoard, alpha, beta, depth+1) # +1 depth for next pair of turns 
            if(alpha > newScore): # alpha pruning
                return (newScore,flag)
            if(currentScore > newScore):
                currentScore = newScore
                currentFlag = flag
            # update beta if currentScore is smaller
            beta = currentScore if currentScore < beta else beta

        # only save the non-pruned result to transposition table
        # because pruned states might have states cause different result
        self.TT[state.ser()] = (currentScore, currentFlag)
        return (currentScore,currentFlag)
                
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

                
        #if terminal state, then return utility of that state
        if(state.is_terminal()):
            # calling utility function with self will always
            # check winning status of current player
            # because in terminal status, the utility is either -1 or 1
            # which is either the highest or lowest value
            # only to compare two cases
            util = state.utility(self)
            self.TT[state.ser()] = (util,True)
            return (util, True)

        # first check if state has been saved in transposition table
        if state.ser() in self.TT:
            util,flag = self.TT[state.ser()]
            if (flag):
                return self.TT[state.ser()]
        
        #else, generate new states
        possibleMoves = state.actions()

        #if no possible moves for Max, then call Min again on that same state
        if(len(possibleMoves) == 0):
            # need to switch opponent when calling Min again
            # result function with None action simply returns
            # the same state with opponent
            util,flag = self.Min(state.result(None),alpha,beta, depth)
            self.TT[state.ser()] = (util,flag)
            return (util,flag)

        # maximizing the utility
        currentScore = -1
        currentFlag = None
        for nextMove in possibleMoves:
            newBoard = state.result(nextMove)
            newScore,flag = self.Min(newBoard, alpha, beta, depth) 
            if(beta < newScore): # beta pruning
                return (newScore,flag)
            if(currentScore < newScore):
                currentScore = newScore
                currentFlag = flag
            # update alpha if currentScore is larger
            alpha = currentScore if currentScore > alpha else alpha

        # only save the non-pruned result to transposition table
        # because pruned states might have states cause different result
        self.TT[state.ser()] = (currentScore,currentFlag)
        return (currentScore,currentFlag)

    def evaluate(self, state):
        """
        Evaluates the utility value in opponent's state with the given row
        Because evaluate function is only called in Min function, hence
        it's always evaluating the utility in opponent's turn
        This heuristic function returns value in [-0.5,0.5]
        so it also works well with alpha-beta pruning
        Para: current state
        return: utility value for our player
        """
        # state.player is opponent
        # state.opponent is our player
        player_goal_stones = state.board[state.player_goal_idx]
        opponent_goal_stones = state.board[state.opponent_goal_idx]
        player_pit_stones=0
        for i in xrange(*state.possible_action_range()):
            player_pit_stones += state.board[i]
        total = state.M*state.N
        opponent_pit_stones= total - player_goal_stones-opponent_goal_stones-player_pit_stones
        prefactor = 1.0/2.0/state.M/state.N
        # We use minus sign because this is actually opponent's state
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
        #while (not self.is_time_up()):
        while (self.maxDepth < 10):
            print "Depth level: ", self.maxDepth
            bestUtil = -1
            curFlag = None
            action = possibleMoves[0] # default action
            # running minimax algorithm with alpha-beta prunign to find best move
            # return action with max min_utility
            alpha = -2 # act like -inf
            beta = 2 # act like inf
            for nextMove in possibleMoves:
                # if time is up, return current bestAction
                """
                if (self.is_time_up()):
                    print "time status:",self.is_time_up()
                    return self.bestAction
                """
                newBoard = state.result(nextMove)
                newScore, flag = self.Min(newBoard, alpha, beta, 0) # We are maximizing the min here
                if bestUtil < newScore:
                    bestUtil = newScore
                    curFlag = flag
                    action = nextMove
                    # update alpha since we are doing maximization
                    alpha = newScore
            self.bestAction = action
            self.bestFlag = curFlag
            # terminate search if optimal move found before time limit
            if (self.bestFlag):
            #either win_solution or no_win_solution is found:
                return self.bestAction
                
            self.maxDepth += 1
        # in case break out of while loop
        return self.bestAction
