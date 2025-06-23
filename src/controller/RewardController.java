package Controller;

import DAO.RewardDAO;

public class RewardController {

    public int addRewardPoints(int earnedPoints) {
        int updatedPoints = 0;

        try {
            RewardDAO dao = new RewardDAO();

            // 1️⃣ Get current total points
            int currentPoints = dao.getTotalRewardPoints();

            // 2️⃣ Add new reward
            updatedPoints = currentPoints + earnedPoints;

            // 3️⃣ Update DB
            dao.updateTotalRewardPoints(updatedPoints);

            dao.close();
        } catch (Exception e) {
            System.out.println("RewardController Error: " + e);
        }

        return updatedPoints;
    }
}
