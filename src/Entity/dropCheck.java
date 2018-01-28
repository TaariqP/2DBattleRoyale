package Entity;

import java.util.Optional;

public class dropCheck {

  private boolean hasPickedUp;
  private Optional<Entity> returnedItem;

  public dropCheck(boolean hasPickedUp, Optional<Entity> returnedItem) {
    this.hasPickedUp = hasPickedUp;
    this.returnedItem = returnedItem;
  }

  public boolean isHasPickedUp() {
    return hasPickedUp;
  }

  public boolean existsReturnedItem() {
    return returnedItem.isPresent();
  }

  public Entity getReturnedItem() {
    return returnedItem.get();
  }
}
