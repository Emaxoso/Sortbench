package dev.emax.sortbench.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class SortbenchUtils {

	public static void main(String[] args) {

		System.out.println(Arrays.toString(test(new int[] {10, 4, 1})));

	}

	public static int[] test(int[] test) {


		for (int datasetIndexA = 0; datasetIndexA < (test.length / 2) + 2; datasetIndexA++) {

			int datasetIndexMin = datasetIndexA;
			int datasetIndexMax = datasetIndexA;

			for (int i = datasetIndexA; i < test.length - datasetIndexA; i++) {
				if (test[i] < test[datasetIndexMin]) datasetIndexMin = i;
				if (test[i] > test[datasetIndexMax]) datasetIndexMax = i;
			}

			arraySwap(test, test.length - datasetIndexA - 1, datasetIndexMax);
			arraySwap(test, datasetIndexA, datasetIndexMin);
		}

		return test;
	}

	public static <T> T createClass(Class<T> classType) {
		try {
			return classType.newInstance();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] arrayList(Class<T> listType, Collection<T> list) {
		return list.toArray((T[]) Array.newInstance(listType, list.size()));
	}

	public static int[] arrayClone(int[] arrayOriginal) {
		return Arrays.copyOf(arrayOriginal, arrayOriginal.length);
	}

	public static <T> T[] arrayClone(T[] arrayOriginal) {
		return Arrays.copyOf(arrayOriginal, arrayOriginal.length);
	}

	public static int[] arrayFill(int[] array, Function<Integer, Integer> arrayElementSupplier) {
		for (int arrayIndex = 0; arrayIndex < array.length; arrayIndex++) {
			array[arrayIndex] = arrayElementSupplier.apply(arrayIndex);
		}
		return array;
	}

	public static int[] arrayFill(int[] array, Supplier<Integer> arrayElementSupplier) {
		for (int arrayIndex = 0; arrayIndex < array.length; arrayIndex++) {
			array[arrayIndex] = arrayElementSupplier.get();
		}
		return array;
	}

	public static <T> T[] arrayFill(T[] array, Function<Integer, T> arrayElementSupplier) {
		for (int arrayIndex = 0; arrayIndex < array.length; arrayIndex++) {
			array[arrayIndex] = arrayElementSupplier.apply(arrayIndex);
		}
		return array;
	}

	public static <T> T[] arrayFill(T[] array, Supplier<T> arrayElementSupplier) {
		for (int arrayIndex = 0; arrayIndex < array.length; arrayIndex++) {
			array[arrayIndex] = arrayElementSupplier.get();
		}
		return array;
	}

	public static void arraySwap(int[] array, int arrayIndexA, int arrayIndexB) {
		int arrayValueA = array[arrayIndexA];
		int arrayValueB = array[arrayIndexB];

		array[arrayIndexA] = arrayValueB;
		array[arrayIndexB] = arrayValueA;
	}

	public static boolean arraySortTest(int[] array) {
		return IntStream
				.range(0, array.length - 1)
				.noneMatch(i -> array[i] > array[i + 1]);
	}

	public static float map(float x, float in_min, float in_max, float out_min, float out_max) {
	     return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
	     return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public static BufferedImage toBufferedImage(Image img) {
	    if (img instanceof BufferedImage) {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

}
