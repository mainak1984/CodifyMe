package edu.codifyme.leetcode.interview.mocktest.google.sortnsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * MEDIUM: Exam Room
 * https://leetcode.com/problems/exam-room/
 *
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If
 * there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then
 * the student sits at seat number 0.)
 *
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat
 * the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room. It
 * is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
 *
 * Example 1:
 * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student sits at the last seat number 5.
 *
 * Note:
 * 1 <= N <= 10^9
 * ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 * Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 *
 * Approach:
 * Use a sort data structure like Priority queue and store all the partitions. Find the position of fragments joining
 * when any student leaves
 *
 * Alternate Approach: Maintain Sorted Positions
 * Intuition
 * We'll maintain ExamRoom.students, a sorted list (TreeSet in Java) of positions the students are currently seated in.
 *
 * Algorithm
 * The ExamRoom.leave(p) operation is clear - we will just list.remove (or TreeSet.remove) the student from
 * ExamRoom.students.
 * Let's focus on the ExamRoom.seat() : int operation. For each pair of adjacent students i and j, the maximum distance
 * to the closest student is d = (j - i) / 2, achieved in the left-most seat i + d. Otherwise, we could also sit in the
 * left-most seat, or the right-most seat.
 * Finally, we should handle the case when there are no students separately.
 */
public class ExamRoom {
    int n;
    PriorityQueue<Interval> pq;

    public ExamRoom(int N) {
        this.n = N;
        this.pq = new PriorityQueue<Interval>( (i1, i2) -> i1.l!=i2.l? i2.l-i1.l: i1.s-i2.s );
        pq.offer(new Interval(0, n-1));
    }

    public int seat(){
        Interval curr = pq.poll();
        int result;
        if(curr.s==0){
            result = 0;
        }else if(curr.e==n-1){
            result = n-1;
        }else{
            result = curr.s+curr.l;
        }
        if(result>curr.s){
            pq.offer(new Interval(curr.s, result-1));
        }
        if(curr.e>result){
            pq.offer(new Interval(result+1, curr.e));
        }
        return result;
    }

    public void leave(int p){
        List<Interval> list = new ArrayList<>(pq);
        Interval prev = null;
        Interval next = null;
        for(Interval i: list){
            if(i.e+1==p){
                prev = i;
            }
            if(i.s-1==p){
                next = i;
            }
        }
        int s = p;
        int e = p;
        if(prev!=null){
            pq.remove(prev);
            s=prev.s;
        }
        if(next!=null){
            pq.remove(next);
            e=next.e;
        }
        pq.offer(new Interval(s, e));
    }

    class Interval{
        int s;
        int e;
        int l;
        public Interval(int start, int end){
            this.s = start;
            this.e = end;
            if(s==0 || e==n-1){
                this.l=e-s;
            }else{
                this.l= (e-s)/2;
            }
        }
    }

    // Alternate Approach:
//    int N;
//    TreeSet<Integer> students;
//
//    public ExamRoom(int N) {
//        this.N = N;
//        students = new TreeSet();
//    }
//
//    public int seat() {
//        //Let's determine student, the position of the next
//        //student to sit down.
//        int student = 0;
//        if (students.size() > 0) {
//            //Tenatively, dist is the distance to the closest student,
//            //which is achieved by sitting in the position 'student'.
//            //We start by considering the left-most seat.
//            int dist = students.first();
//            Integer prev = null;
//            for (Integer s: students) {
//                if (prev != null) {
//                    //For each pair of adjacent students in positions (prev, s),
//                    //d is the distance to the closest student;
//                    //achieved at position prev + d.
//                    int d = (s - prev) / 2;
//                    if (d > dist) {
//                        dist = d;
//                        student = prev + d;
//                    }
//                }
//                prev = s;
//            }
//
//            //Considering the right-most seat.
//            if (N - 1 - students.last() > dist)
//                student = N - 1;
//        }
//
//        //Add the student to our sorted TreeSet of positions.
//        students.add(student);
//        return student;
//    }
//
//    public void leave(int p) {
//        students.remove(p);
//    }
}
