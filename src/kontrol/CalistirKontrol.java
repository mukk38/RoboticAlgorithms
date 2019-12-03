package kontrol;





public final class CalistirKontrol {

	private static CalistirKontrol calistirKontrol = null;
	
	private CalistirKontrol(){
		
	}
	public static CalistirKontrol getInstance(){
		if (calistirKontrol == null) {
			calistirKontrol = new CalistirKontrol();
			
		}
		return calistirKontrol;
	}

	
//	public static void calistirDugmesineTiklandi(){
//
//        // -1 = blocked
//        // 0+ = additional movement cost
//		int[][] maze = engellerdenMazeOlustur();
////        int[][] maze = {
////            {  0,  0,  0,  0,  0,  0,  0,  0},
////            {  0,  0,  0,  0,  0,  0,  0,  0},
////            {  0,  0,  0,100,100,100,  0,  0},
////            {  0,  0,  0,  0,  0,100,  0,  0},
////            {  0,  0,100,  0,  0,100,  0,  0},
////            {  0,  0,100,  0,  0,100,  0,  0},
////            {  0,  0,100,100,100,100,  0,  0},
////            {  0,  0,  0,  0,  0,  0,  0,  0},
////        };
//        AStar as = new AStar(maze, (int)GlobalDegiskenler.getBaslangicNoktasi().getX(),(int)GlobalDegiskenler.getBaslangicNoktasi().getY(), true);
//        List<Node> path = as.findPathTo((int)GlobalDegiskenler.getBitisNoktasi().getX(), (int)GlobalDegiskenler.getBitisNoktasi().getY());
//
//        if (path != null) {
//            path.forEach((n) -> {
//                System.out.print("[" + n.x + ", " + n.y + "] ");
//                maze[n.y][n.x] = -1;
//            });
//            System.out.printf("\nTotal cost: %.02f\n", path.get(path.size() - 1).g);
//            Writer writer =null;
//            try {
//            	writer = new BufferedWriter(new OutputStreamWriter(
//				        new FileOutputStream("filename.txt"), "utf-8"));
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            for (int[] maze_row : maze) {
//                for (int maze_entry : maze_row) {
//                    switch (maze_entry) {
//                        case 0:
//                            System.out.print("_");
//						try {
//							writer.write("_");
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                            break;
//                        case -1:
//                            System.out.print("*");
//						try {
//							writer.write("*");
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                            break;
//                        default:
//                            System.out.print("#");
//						try {
//							writer.write("#");
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                    }
//                }
//                try {
//					writer.write("\n");
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//                System.out.println();
//            }
//        }
//    
//	}


}
