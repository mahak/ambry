package com.github.ambry.tools.admin;

import java.util.ArrayList;


public class BlobStatus {
  ArrayList<String> available;
  ArrayList<String> deletedOrExpired;
  ArrayList<String> unavailable;
  boolean isDeletedOrExpired;

  public BlobStatus(String replica, boolean isDeletedOrExpired, ArrayList<String> replicaList) {
    available = new ArrayList<String>();
    deletedOrExpired = new ArrayList<String>();
    unavailable = new ArrayList<String>();
    this.isDeletedOrExpired = isDeletedOrExpired;
    if (!isDeletedOrExpired) {
      available.add(replica);
    } else {
      deletedOrExpired.add(replica);
    }
    unavailable.addAll(replicaList);
    unavailable.remove(replica);
  }

  ArrayList<String> getAvailable() {
    return available;
  }

  void addAvailable(String replica) {
    if (!deletedOrExpired.contains(replica)) {
      this.available.add(replica);
      this.unavailable.remove(replica);
    }
  }

  ArrayList<String> getDeletedOrExpired() {
    return deletedOrExpired;
  }

  ArrayList<String> getUnavailableList() {
    return unavailable;
  }

  void addDeletedOrExpired(String replica) {
    this.deletedOrExpired.add(replica);
    this.isDeletedOrExpired = true;
    this.unavailable.remove(replica);
  }

  boolean getIsDeletedOrExpired() {
    return isDeletedOrExpired;
  }

  public String toString() {
    int totalReplicas = available.size() + deletedOrExpired.size() + unavailable.size();
    String msg =
        "Available size: " + available.size() + " Available :: " + available
            + "\nDeleted/Expired size: " + deletedOrExpired.size() + "Deleted/Expired :: " + deletedOrExpired
            + "\nUnavailable size: " + unavailable.size() + "Unavailable :: " + unavailable
            + "\nTotal Replica count: " + totalReplicas;
    return msg;
  }
}