package jp.co.wap.exam.lib;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.wap.exam.lib.Interval;

/**
 * The class Problem2 is to solve exam1 problem2.
 */
public class Problem2 {
	public int getMaxWorkingTime(List<Interval> intervals) {
		// Return 0 if the argument is null or an empty list
		if (intervals == null || intervals.size() == 0)
			return 0;

		// Sort the list by time
		Collections.sort(intervals, new ComparatorByEndMinute());
		// TODO

		int[] ans = new int[intervals.size()];
		for (int i = 0; i < intervals.size(); i++) {
			int l = 0;
			int r = i - 1;
			int beginTime = intervals.get(i).getBeginMinuteUnit();
			while (l <= r) {
				int mid = (l + r) / 2;
				if (intervals.get(mid).getEndMinuteUnit() < beginTime)
					l = mid + 1;
				else
					r = mid - 1;
			}
			if (i > 0 && r >= 0)
				ans[i] = Math.max(
						intervals.get(i).getIntervalMinute() + ans[r],
						ans[i - 1]);
			else
				ans[i] = intervals.get(i).getIntervalMinute();

		}
		return ans[intervals.size() - 1];

	}
}

class ComparatorByEndMinute implements Comparator<Interval> {

	@Override
	public int compare(Interval arg0, Interval arg1) {
		return arg0.getEndMinuteUnit() - arg1.getEndMinuteUnit();
	}
}
