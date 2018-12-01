package Sequence;

import java.util.Scanner;

public class Sequence<E> {
	protected Object[] data;
	protected int size;

	public Sequence(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		this.data = new Object[n];
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public void append(E element) {
		if (this.size < this.data.length) {
			this.data[this.size] = element;
		} else {
			Object[] temp = new Object[this.size + 1];
			System.arraycopy(this.data, 0, temp, 0, this.size);
			this.data = temp;
			this.data[this.size] = element;
		}
		this.size++;
	}

	public Object get(int k) throws IndexOutOfBoundsException {
		if (k < 0 || k >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		return this.data[k];
	}

	public void print() {
		System.out.print("\nCurrent Sequence: ");
		for (int i = 0; i < this.size(); i++) {
			System.out.print(this.get(i) + " ");
		}
		System.out.println();
	}

	public void insert(int index, E newElement) {
		// IMPLEMENT ME

		if (index > this.size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			Object[] temp = new Object[this.size + 1];
			for (int i = 0; i < this.size + 1; i++) {
				if (i < index) {
					temp[i] = this.data[i];
				} else if (i == index) {
					temp[i] = newElement;
				} else {
					temp[i] = this.data[i - 1];
				}
			}
			this.data = temp;
			this.size++;
		}

		/*
		 * if (index > this.size || index < 0) { throw new IndexOutOfBoundsException();
		 * } else { Object[] temp = new Object[this.size + 1];
		 * System.arraycopy(this.data, 0, temp, 0, index); System.arraycopy(this.data,
		 * index, temp, index + 1, this.size - index); this.data = temp;
		 * this.data[index] = newElement; this.size++; }
		 */
	}

	public void delete(int index) {
		// IMPLEMENT ME

		if (index > this.size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			Object[] temp = new Object[this.size - 1];
			for (int i = 0; i < this.size; i++) {
				if (i == index) {
					continue;
				} else if (i < index) {
					temp[i] = this.data[i];
				} else if (i > index) {
					temp[i - 1] = this.data[i];
				}
			}
			this.data = temp;
			this.size--;
		}
	}

	/*
	 * if (index > this.size || index < 0) { throw new IndexOutOfBoundsException();
	 * } else { Object[] temp = new Object[this.size - 1];
	 * System.arraycopy(this.data, 0, temp, 0, index); System.arraycopy(this.data,
	 * index + 1, temp, index, this.size - index - 1); this.data = temp;
	 * this.size--; }
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Sequence<Integer> s = new Sequence<Integer>(5);
		s.append(1);
		s.append(2);
		s.append(3);
		s.append(4);
		s.append(5);
		s.print();

		// INSERT CONTROL LOOP HERE
		System.out.println("Please enter 1 to insert, 2 to delete, or 3 to quit: ");

		if (in.nextInt() == 1) {
			System.out.println("Please enter the value to insert: ");
			int insertValue = in.nextInt();
			System.out.println("Please enter the index to insert: ");
			int insertIndex = in.nextInt();
			s.insert(insertIndex, insertValue);

		} else if (in.nextInt() == 2) {
			System.out.println("Please enter the index to delete: ");
			int deleteIndex = in.nextInt();
			if (deleteIndex < s.size) {
				s.delete(deleteIndex);
			} else {
				System.out.println("Incorrect length of input, Please try again! \nNothing Changed now!");
			}

		} else if (in.nextInt() == 3) {
			System.out.println("Thanks for playing this, Goodbye!");

		}

		in.close();
		
	} // End main
} // End class
