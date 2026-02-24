import java.util.*;

public class MockCatanAgent1 implements CatanAgent {
    private int playerId;

    @Override
    public void init(int playerId) { 
        this.playerId = playerId; 
    }

    @Override
    public Move chooseInitialSettlement(GameState s) { return s.getLegalSettlements().get(0); }

    @Override
    public Move chooseInitialRoad(GameState s) { return s.getLegalRoads().get(0); }

    @Override
    public Move chooseMove(GameState s) { return new Move(MoveType.PASS); }

    @Override
    public Map<ResourceType, Integer> chooseDiscard(GameState s, int count) {
        return new HashMap<>(); 
    }

    @Override
    public ResourceType chooseResource(GameState s) { return ResourceType.GRAIN; }

    @Override
    public int chooseRobberTarget(GameState s, List<Integer> targets) {
        return targets.isEmpty() ? -1 : targets.get(0);
    }

    @Override
    public DevelopmentCard chooseDevelopmentCard(GameState s) { return null; }
}