INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('6.00', 'Una pizza che incarna la semplicità e l\'eleganza dell\'Italia. La base di pomodoro fresco è coronata da una soffice mozzarella e impreziosita da foglie di basilico aromatico. Un\'esplosione di sapori tradizionali che ti faranno sentire come se fossi seduto in una piazza italiana al tramonto.', 'Margherita', 'https://www.scattidigusto.it/wp-content/uploads/2018/03/pizza-margherita-originale-Scatti-di-Gusto-1280x670.jpg');
INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('8.00', 'Questa pizza è un\'armoniosa sinfonia di gusti e consistenze. La base di pomodoro si sposa con la morbida mozzarella, mentre il prosciutto cotto, delicato e saporito, si unisce ai funghi terrosi e aromatici. Ogni morso è un viaggio nei boschi e nei prati d\'Italia.', 'Prosciutto e funghi', 'https://upload.wikimedia.org/wikipedia/commons/6/6f/2014_Pizza_con_funghi_e_prosciutto_cotto_e_mozzarella_di_bufala.jpg');
INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('8.50', 'Preparati a un\'avventura gustativa audace con questa pizza che ha il giusto tocco di piccantezza. La base di pomodoro accoglie la mozzarella cremosa, mentre il salame piccante affettato aggiunge un pizzico di fuoco che accenderà i tuoi sensi. Una combinazione esplosiva di sapori per gli amanti del piccante.', 'Salame piccante', 'https://www.davesamericanfood.com/wp-content/uploads/2020/04/pepperoni-pizza-2048x1356.jpg.webp');
INSERT INTO `pizzas` (`price`, `description`, `name`, `photo_url`) VALUES ('9.00', 'Questa pizza è una festa per i palati audaci e giocosi. La base di pomodoro si unisce alla mozzarella filante, mentre i wurstel tagliati a rondelle offrono un\'esplosione di gusto affumicato e succoso. Le patatine croccanti completano questa combinazione straordinaria, aggiungendo una deliziosa texture e un tocco di divertimento ad ogni morso.', 'Wurstel e patatine', 'https://www.scattidigusto.it/wp-content/uploads/2015/11/pizza-wurstel-patatine-pomodoro-1280x853.jpg');
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
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (1, 1);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (1, 2);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (1, 3);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (2, 1);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (2, 4);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (2, 5);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (2, 6);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (3, 1);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (3, 2);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (3, 7);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (4, 1);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (4, 2);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (4, 8);
INSERT INTO `pizza_ingredient` (`pizza_id`, `ingredient_id`) VALUES (4, 9);







