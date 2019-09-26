package br.com.sotero.checklistsmk.utils;

public class ChkUtills {

	public static boolean isNullOrEmpty(Object object) {
		if (object == null) {
			return true;
		}

		if (object instanceof Long) {
			return ((Long) object).longValue() == 0L;
		}

		return true;
	}
}
