INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('6.00', 'pomodoro, mozzarella, basilico', 'Margherita', 'https://www.scattidigusto.it/wp-content/uploads/2018/03/pizza-margherita-originale-Scatti-di-Gusto-1280x670.jpg');
INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('8.00', 'pomodoro, mozzarella di bufala, prosciutto cotto, funghi', 'Prosciutto e funghi', 'https://upload.wikimedia.org/wikipedia/commons/6/6f/2014_Pizza_con_funghi_e_prosciutto_cotto_e_mozzarella_di_bufala.jpg');
INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('8.50', 'pomodoro, mozzarella, salame piccante', 'Salame piccante', 'https://www.davesamericanfood.com/wp-content/uploads/2020/04/pepperoni-pizza-2048x1356.jpg.webp');
INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('9.00', 'pomodoro, mozzarella, wurstel, patatine', 'Wurstel e patatine', 'https://www.scattidigusto.it/wp-content/uploads/2015/11/pizza-wurstel-patatine-pomodoro-1280x853.jpg');
INSERT INTO `offers` (`pizza_id`, `start_offer_date`, `end_offer_date`, `offer_title`) VALUES (1, '2023-06-29', '2023-07-05', 'Menù margherita');
INSERT INTO `offers` (`pizza_id`, `start_offer_date`, `end_offer_date`, `offer_title`) VALUES (1, '2023-06-25', '2023-06-30', 'Bibita in omaggio');
INSERT INTO `offers` (`pizza_id`, `start_offer_date`, `end_offer_date`, `offer_title`) VALUES (2, '2023-06-30', '2023-07-02', 'Offerta del weekend');
INSERT INTO `offers` (`pizza_id`, `start_offer_date`, `end_offer_date`, `offer_title`) VALUES (4, '2023-06-01', '2023-06-30', 'Offerta del mese');
INSERT INTO `offers` (`pizza_id`, `start_offer_date`, `end_offer_date`, `offer_title`) VALUES (4, '2023-07-01', '2023-07-30', 'Speciale Luglio');
INSERT INTO `offers` (`pizza_id`, `start_offer_date`, `end_offer_date`, `offer_title`) VALUES (4, '2023-06-27', '2023-07-01', 'Dolce a metà prezzo');
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Pomodoro", "Tipologia ciliegino. Il termine “ciligino” indica un pomodorino di piccole dimensioni, e tondo proprio come una ciliegia.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Mozzarella", "Mozzarella fiordilatte proveniente dalla provincia di Napoli.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Basilico", "Il basilico napoletano dal sapore delicato.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Mozzarella di bufala", "La Mozzarella di Bufala Campana è l'unica mozzarella realizzata solo con latte di bufala ad aver ottenuto il marchio DOP.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Prosciutto cotto", "Il prosciutto cotto San Giovanni di Angelo Capitelli che è stato eletto il miglior cotto d'Italia secondo la Guida Salumi d'Italia 2023.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Funghi", "Funghi porcini freschi.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Salame piccante", "Salame delicatamente piccante.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Wurstel", "Wurstel tagliato a rondelle.");
INSERT INTO `ingredients` (`name`, `description`) VALUES ("Patatine", "Patatine fritte.");




