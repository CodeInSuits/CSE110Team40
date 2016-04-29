So below are some of the key points to be aware for p4:
1. We use iterative deepening so that we can always have some result before time runs out
2. We have a maximum depth limit (20) just in case infinite tiem is given for large board
3. We use EVAL flag to break out while loop when optimal solution is found
    This is done buy setting EVAL = TRUE whenever evaluate() is called (i.e. not optimal)
    in each iteration, we first set EVAL = False, so if no evaluate() is called
    then we'll break out of while loop
    *   The reason why we are not doing the way TA suggested to Chris is that we don't reach
        all the terminal at the same time. So even finding a terminal state might not give optimal
        solution.
        I actually tried using the method TA suggested (set EVAL to false when terminal is reached)
        But this actually broke the code. I guess the TA sugggested was probably for a diferent
        design choice
4. For transposition table, we store utility value and the corresponding 'depthX' value. The depthX
    value is used to track how good the stored value is, DepthX = Max Depth - depth during finding
    iteration. Basically it indicateds how many depth can be expanded under current state. So as I
    mentioned in the conversation earlier, the same state found at depth 4 with max depth 5 is as
    good as the same state found at depth 9 with max depth 10 since their difference is both 1.
5. With 4 being said, we set depthX to be 0 during time up situation, since it was called during time
    -up so it is least reliable. and we also set all terminal utility to have depthX = 20 because
    if a terminal state is reached, its value is already optimal regardless when we find it.
6. Our depth is defined to be a full turn of Min/Max, i.e. depth 1 consists of one call of Max and one
    call of Min. It doesn't have any advantage over the single turn definition. But I guess it satisfies
    my symmetry ego?
7. WIth 6's definition of depth, you should that we only +1 to depth in Min's call for Max and use the
    same depth when calling Min() in Max()
8. I modified the evaluate() function to actually use my_row as opposed to the case in p3. Because in
    p3 we only call evaluae() in opponent's state, so my_row is unnecessary. But in p4, we call evaluate()
    in both cases, so we need a way to distinguish which player we are calculating the utility. You can see
    how my_row is used in evaluate(). So if you are using a different heuristic function, please take this
    into consideration.
9. Above should be all I can think of right now that might help you understand my code/ write report. Let 
    me know if you have any other questions.
10. Good luck guys!
