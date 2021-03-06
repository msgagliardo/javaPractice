/*Marc Gagliardo							September 21, 2015
  CPS593.02									  hw1 Statistician*/

public class Statistician {
	private double max;
	private double min;
	private int size;
	private double sum;
	
	public Statistician() {
		
	}
	public void next(double number) {
		if (number > max)
			max = number;
		if (number < min)
			min = number;
		
		sum += number;
		size += 1;
	}
	public double sum() {
		if (sum > Double.MAX_VALUE)
			return Double.POSITIVE_INFINITY;
		else if (sum < -Double.MAX_VALUE)
			return Double.NEGATIVE_INFINITY;
		else 
			return sum;
	}
	public double mean() {
		return sum() / length();
	}
	public int length() {
		return size;
	}
	public double maximum() {
		if (length() == 0)
			return Double.NaN;
		else 
			return max;
	}
	public double minimum() {
		if (length() == 0)
			return Double.NaN;
		else
			return min;
	}
	public boolean equals(Object obj) {
		
		if (obj instanceof Statistician) {
			Statistician candidate = (Statistician)obj;
			return (length() == candidate.length()) && (sum() == candidate.sum())
					&& (mean() == candidate.mean()) && (maximum() == candidate.maximum())
					&& (minimum() == candidate.minimum());
		}else
			return false;
	}
	public void clear() {
		max = 0;
		min = 0;
		size = 0;
		sum = 0;
	}
	public void addAll(Statistician addend) {
		if (addend == null)
			throw new NullPointerException("An argument with a null value was entered.");
		else {
			if (addend.max > max)
				max = addend.max;
			if (addend.min < min)
				min = addend.min;
			
			sum += addend.sum;
			size += addend.size;
		}	
	}
	public static Statistician union(Statistician s1, Statistician s2) {
		Statistician s3 = new Statistician();
		
		if ((s1 == null) || (s2 == null))
			throw new NullPointerException("One of the Arguments entered "
					+ "has a null value.");
		
		if (s1.max > s2.max)
			s3.max = s1.max;
		else
			s3.max = s2.max;
		
		if (s1.min < s2.min)
			s3.min = s1.min;
		else 
			s3.min = s2.min;
		
		s3.size = s1.size + s2.size;
		s3.sum = s1.sum + s2.sum;
		
		return s3;
	}
}
