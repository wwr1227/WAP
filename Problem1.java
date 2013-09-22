package jp.co.wap.exam.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.wap.exam.lib.Interval;

/**
 * The class Problem1 is to solve the exam1 problem1.
 */
public class Problem1 {

	public int getMaxIntervalOverlapCount(List<Interval> intervals) {

		// Return 0 if the argument is null or an empty list
		if (intervals == null || intervals.size() == 0)
			return 0;

		// Create a new List contains all time info
		List<TimeLine> timelineList = new ArrayList<TimeLine>();
		timelineList.clear();
		for (int i = 0; i < intervals.size(); i++) {
			timelineList.add(new TimeLine("begin", intervals.get(i)
					.getBeginMinuteUnit()));
			timelineList.add(new TimeLine("end", intervals.get(i)
					.getEndMinuteUnit()));
		}

		// Sort the list by time
		Collections.sort(timelineList, new ComparatorListSort());

		// Calculate overlap counts
		int overlapCount = 1;
		int overlapCountTemp = 0;
		for (int i = 0; i < timelineList.size(); i++) {
			if (timelineList.get(i).getType().equals("begin")) {
				overlapCountTemp++;
			} else if (timelineList.get(i).getType().equals("end")) {
				overlapCountTemp--;
			}
			if (overlapCountTemp > overlapCount) {
				overlapCount = overlapCountTemp;
			}
		}
		// TODO
		return overlapCount;
	}

	public int getMin(int a, int b) {
		return a > b ? b : a;
	}
}

class TimeLine {
	int time;
	String type;

	TimeLine(String type, int time) {
		this.time = time;
		this.type = type;
	}

	public int getTime() {
		return this.time;
	}

	public String getType() {
		return this.type;
	}
}

class ComparatorListSort implements Comparator<TimeLine> {

	@Override
	public int compare(TimeLine arg0, TimeLine arg1) {
		if (arg0.getTime() > arg1.getTime())
			return 1;
		else if (arg0.getTime() == arg1.getTime()) {
			if (arg0.type.equals(arg1.type))
				return 0;
			else if (arg0.type.equals("begin") && arg1.type.equals("end"))
				return -1;
			else
				return 1;
		} else {
			return -1;
		}
	}
}
