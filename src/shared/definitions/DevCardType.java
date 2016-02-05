package shared.definitions;

public enum DevCardType
{
	
	SOLDIER, YEAR_OF_PLENTY, MONOPOLY, ROAD_BUILD, MONUMENT;
	@Override
	public String toString() {
		switch(this) {
			case SOLDIER: return "Soldier";
			case YEAR_OF_PLENTY: return "Year_of_Plenty";
			case MONOPOLY: return "Monopoly";
			case ROAD_BUILD: return "Road_Build";
			case MONUMENT: return "Monument";
			default: throw new IllegalArgumentException();
		}
	}
}

