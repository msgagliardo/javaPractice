/*Marc Gagliardo (N03345408)						   October 15, 2015
  CPS593.02														   hw2*/
  
public class DoubleArraySeq implements Cloneable {
	private double[] data;
	private int manyItems;
	private int currentIndex;
	
	public DoubleArraySeq() {
		final int INITIAL_CAPACITY = 10;
		data = new double[INITIAL_CAPACITY];
		manyItems = 0;
		currentIndex = manyItems;
	}
	public DoubleArraySeq(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException();
		else {
			data = new double[initialCapacity];
			manyItems = 0;
			currentIndex = manyItems;
		}
	}
	public void addAfter(double element) {
		if (manyItems == data.length) {
			ensureCapacity((2*data.length));
		}
		
		if (currentIndex == manyItems) {
			data[manyItems] = element;
			manyItems++;
		}else if (manyItems - currentIndex == 1) {
			data[manyItems] = element;
			currentIndex++;
			manyItems++;
		}else {
			for (int i = manyItems - 1; i >= currentIndex + 1; i--)
				data[i + 1] = data[i];
			data[currentIndex + 1] = element;
			currentIndex++;
			manyItems++;
		}	
	}
	public void addBefore(double element) {
		if (manyItems == data.length)
			ensureCapacity((2*data.length) + 1);
		
		if (currentIndex == manyItems) {
			for (int i = manyItems - 1; i >= 0; i--) 
				data[i + 1] = data[i];
			data[0] = element;
			currentIndex = 0;
			manyItems++;
		}else {
			for (int i = manyItems - 1; i >= currentIndex; i--)
				data[i + 1] = data[i];
			data[currentIndex] = element;
			manyItems++;
		}
	}
	public void addAll(DoubleArraySeq addend) {
		if (addend == null)
			throw new NullPointerException();
		ensureCapacity(manyItems + addend.manyItems);
		System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
		manyItems += addend.manyItems;
	}
	public void advance() {
		if (!isCurrent()) {
			throw new IllegalStateException("There is no current index "
					+ "to advance."); 
		}else 
			currentIndex++;
	}
	public DoubleArraySeq clone() {
		DoubleArraySeq answer;
		
		try {
			answer = (DoubleArraySeq)super.clone();
		}catch (CloneNotSupportedException e) {
			throw new RuntimeException("This class does not implement Cloneable.");
		}
		answer.data = data.clone();
		return answer;
	}
	public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2) {
		DoubleArraySeq s3 = new DoubleArraySeq(s1.manyItems + s2.manyItems);
		
		if (s1 == null || s2 == null)
			throw new NullPointerException("One of the arguments is null.");
		
		System.arraycopy(s1.data, 0, s3.data, 0, s1.manyItems);
		System.arraycopy(s2.data, 0, s3.data, s1.manyItems, s2.manyItems);
		
		s3.manyItems = s1.manyItems + s2.manyItems;
		s3.currentIndex = s3.manyItems;
		return s3;
	}
	public void ensureCapacity(int minimumCapacity) {
		if (data.length >= minimumCapacity)
			System.err.println("The capacity is already >= this capacity.");
		else {
			double[] newData = new double[minimumCapacity];
			System.arraycopy(data, 0, newData, 0, manyItems);
			data = newData;
		}
	}
	public int getCapacity() {
		return data.length;
	}
	public double getCurrent() {
		if (!isCurrent())
			throw new IllegalStateException("there is no current element");
		else 
			return data[currentIndex];
	}
	public boolean isCurrent() {
		if (currentIndex == manyItems)
			return false;
		else
			return true;
	}
	public void removeCurrent() {
		if (!isCurrent())
			throw new IllegalStateException("there is no current element");
		
		if (currentIndex == manyItems - 1)
			manyItems--;
		else {
			int i;
			
			for (i = currentIndex + 1; i < manyItems; i++) 
				data[i - 1] = data[i];
			
			manyItems--;
		}
	}
	public int size() {
		return manyItems;
	}
	public void start() {
		currentIndex = 0;
	}
	public void trimToSize() {
		double[] trimmedArray;
		
		if (data.length != manyItems) {
			trimmedArray = new double[manyItems];
			System.arraycopy(data, 0, trimmedArray, 0, manyItems);
			data = trimmedArray;
		}
	}
	public void print() {
		System.out.println("   capacity = " + getCapacity());
		System.out.println("   length = " + size());
		try {
			System.out.println("   current element = " + getCurrent());
		}catch (IllegalStateException e) {
			System.err.println("   " + e.getMessage());
		}
		System.out.print("   elements:   ");
		
		for (int i = 0; i < manyItems; i++)
			System.out.print(data[i] + "  ");
		System.out.print("\n\n");
		
	}
}
