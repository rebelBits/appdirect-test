CREATE TABLE IF NOT EXISTS subscriptions
(
  id SERIAL,
  marketplaceUrl VARCHAR(255),
  creatoropenId VARCHAR(255),  
  accountStatus varchar(255),
  editionCode VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users
(
  id SERIAL,
  uid VARCHAR(255),
  firstName VARCHAR(255),
  lastName VARCHAR(255),
  openId VARCHAR(255),
  subscriptionId INTEGER
);