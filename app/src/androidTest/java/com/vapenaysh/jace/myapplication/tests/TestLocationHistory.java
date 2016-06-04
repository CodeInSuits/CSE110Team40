package com.vapenaysh.jace.myapplication.tests;

/**
 * Story 1: Display a list of my partner’s daily location history
 *
 *
 * Scenario 1: My partner doesn’t visit any one of the favorite locations
 * Given that I have a partner
 * And my partner has added a favorite location,
 * when my partner haven’t visited any of his/her favorite locations since 3:00am at that day
 * Then the list of my partner’s visited history will be an empty list.
 *
 * Scenario 2: My partner visits one of the favorite locations
 * Given that I have a partner
 * And my partner has added a favorite location,
 * when my partner visits the given location
 * Then the name of the my partner’s favorite location will appear in my list ordered by
 * the most recent visits.
 *
 *
 * Created by XuanpeiEstherOuyang on 5/27/16.
 */
public class TestLocationHistory {



}
