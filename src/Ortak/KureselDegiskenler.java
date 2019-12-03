package Ortak;

import java.util.ArrayList;
import java.util.Vector;

import dwa2.RobotState;

public final class KureselDegiskenler {
	private static boolean potentialFieldBaslatidi = false;
	private static boolean dynamicWindowsApproachBaslatidi = false;
	private static boolean prmBaglaTiklandi = false;
	private static ArrayList<Vector<RobotState>> dynamicWindowsPathler = new ArrayList<Vector<RobotState>>();
	public static boolean isPotentialFieldBaslatidi() {
		return potentialFieldBaslatidi;
	}

	public static void setPotentialFieldBaslatidi(boolean potentialFieldBaslatidi) {
		KureselDegiskenler.potentialFieldBaslatidi = potentialFieldBaslatidi;
	}

	public static String getImajAdiGetir(int robotIndeks) {
		switch (robotIndeks) {
		case 0:
			return OrtakSabitler.GEZILEN_YER_DOSYA_PATH;
			
	case 1:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH2;
			
	case 2:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH3;
	case 3:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH4;
	case 4:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH5;
	case 5:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH6;
	case 6:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH7;
	case 7:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH8;
	case 8:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH9;
	case 9:
		return OrtakSabitler.GEZILEN_YER_DOSYA_PATH10;
	
		

		default:
			return OrtakSabitler.GEZILEN_YER_DOSYA_PATH;
		}

	}

	public static boolean isDynamicWindowsApproachBaslatidi() {
		return dynamicWindowsApproachBaslatidi;
	}

	public static void setDynamicWindowsApproachBaslatidi(boolean dynamicWindowsApproachBaslatidi) {
		KureselDegiskenler.dynamicWindowsApproachBaslatidi = dynamicWindowsApproachBaslatidi;
	}

	public static ArrayList<Vector<RobotState>> getDynamicWindowsPathler() {
		return dynamicWindowsPathler;
	}

	public void setDynamicWindowsPathler(ArrayList<Vector<RobotState>> dynamicWindowsPathler) {
		this.dynamicWindowsPathler = dynamicWindowsPathler;
	}

	public static boolean isPrmBaglaTiklandi() {
		return prmBaglaTiklandi;
	}

	public static void setPrmBaglaTiklandi(boolean prmBaglaTiklandi) {
		KureselDegiskenler.prmBaglaTiklandi = prmBaglaTiklandi;
	}


}
