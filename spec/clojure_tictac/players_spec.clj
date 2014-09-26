(ns clojure_tictac.players-spec
  (:require [speclj.core :refer :all]
            [clojure_tictac.core :refer :all]
            [clojure_tictac.board :refer :all]
            [clojure_tictac.ttt-rules :refer [x o]]
            [clojure_tictac.players :refer :all]))

(describe "players"
	(with empty-board [nil nil nil nil nil nil nil nil nil])
	(def human (clojure_tictac.players.Human. x))
	(def computer (clojure_tictac.players.Computer. o))

	(it "creates players"
		(should= human 
						(create-player "Human" x)))

	(context "human"	
		(it "should have a mark"
			(should= x 
							(:marker human)))

		(it "has an opponent"
			(should= o 
							(opponent (:marker human))))

		(it "can make a move"
			(should= 1 (with-redefs [read-line (constantly "1")]
							(move human @empty-board)))))

	(context "Computer"
		(it "should have a mark"
			(should= o 
							(:marker computer)))

		(it "has an opponent"
			(should= x 
							(opponent (:marker computer))))

		(it "plays in center if human chooses corner"
			(should= 4 (move
									computer
									[x nil nil
									nil nil nil
									nil nil nil] )))

		(it "chooses the winning move over blocking move"
			(should= 2 (move
									computer
									[o 	o nil
									 x nil x
									 x nil nil] )))

		(it "chooses a blocking move"
			(should= 2 (move 
									computer
									[x x nil
									 nil o nil 
									 nil nil nil])))

		(it "chooses first move"
			(should= 0 (move
									computer
									@empty-board)))

		(it "tries to win"
			(should= 1 (move
									computer
									[o nil nil
									nil x nil
									nil nil nil])))))