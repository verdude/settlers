package shared.locations;

/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation
{
	
	private HexLocation hexLoc;
	private VertexDirection dir;
	private EdgeDirection direction;


	private int x;
	private int y;
	
	public VertexLocation(HexLocation hexLoc, VertexDirection direction)
	{
		setHexLoc(hexLoc);
		setDirection(direction);
		this.x = hexLoc.getX();
		this.y = hexLoc.getY();
		this.direction = EdgeDirection.North.fromString(direction.toString());
	}
	
	public HexLocation getHexLoc()
	{
		if(hexLoc == null){
			return new HexLocation(x,y);
		}

		return hexLoc;
	}
	public void setVertexDirection(VertexDirection direction){
		this.dir = direction;
	}
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}
	
	public VertexDirection getDirection()
	{

		if(dir == null){
			dir = VertexDirection.East.fromString(direction.toString());
		}
		return dir;
	}
	
	private void setDirection(VertexDirection direction)
	{
		this.dir = direction;
	}
	
	@Override
	public String toString()
	{
		return "VertexLocation [hexLoc=" + hexLoc + ", direction=" + direction + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		VertexLocation other = (VertexLocation)obj;
		if(direction != other.direction)
			return false;
		if(hexLoc == null)
		{
			if(other.hexLoc != null)
				return false;
		}
		else if(!hexLoc.equals(other.hexLoc))
			return false;
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this vertex location. Since
	 * each vertex has three different locations on a map, this method converts
	 * a vertex location to a single canonical form. This is useful for using
	 * vertex locations as map keys.
	 * 
	 * @return Normalized vertex location
	 */
	public VertexLocation getNormalizedLocation()
	{

		if(dir == null){
			dir = VertexDirection.East.fromString(direction.toString());
		}
		if(hexLoc == null){
			hexLoc = new HexLocation(x,y);
		}
		// Return location that has direction NW or NE
		
		switch (dir)
		{
			case NorthWest:
			case NorthEast:
				return this;
			case West:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.SouthWest),
										  VertexDirection.NorthEast);
			case SouthWest:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.South),
										  VertexDirection.NorthWest);
			case SouthEast:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.South),
										  VertexDirection.NorthEast);
			case East:
				return new VertexLocation(
										  hexLoc.getNeighborLoc(EdgeDirection.SouthEast),
										  VertexDirection.NorthWest);
			default:
				assert false;
				return null;
		}
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
//	public EdgeValue toEdgeLoc(){
//
//		String dir = this.getNormalizedLocation().toString();
//		EdgeValue edgeLocation = new EdgeValue(getHexLoc(),EdgeDirection.North.fromString(dir));
//		return edgeLocation;
//	}

}

