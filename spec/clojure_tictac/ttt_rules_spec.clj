(ns clojure_tictac.ttt-rules-spec
  (:require [speclj.core :refer :all]
            [clojure_tictac.core :refer :all]
            [clojure_tictac.board :refer :all]
            [clojure_tictac.ttt-rules :refer :all]))

(describe "ttt rules"
	(with x "x")
	(with o "o")
	(with x-win-board ["x" "x" "x" "o" "o" "x" "o" "x" "o"])
	(with o-win-board ["o" "o" "o" "x" "x" "o" "x" nil nil])
	(with test-board [0 1 2 3 4 5 6 7 8])
	(with rows '((0 1 2) (3 4 5) (6 7 8)))
	(with cols '((0 3 6) (1 4 7) (2 5 8)))
	(with diags '((0 4 8) (2 4 6)))
	(with empty-board [nil nil nil nil nil nil nil nil nil])
	(with draw-board ["x" "o" "x" "o" "x" "x" "o" "x" "o"])
	(with moves-board [nil nil nil "x" "o" "o" "x" "x" "o"])

	(it "returns all winning combinations"
		(should= (concat @rows @cols @diags) (get-winning-combos @test-board)))
	(it "checks for a winner"
		(should= true (winner? @x-win-board))
		(should= true (winner? @o-win-board))
		(should= false (winner? @draw-board)))
	(it "determines draws"
		(should= false (draw? @empty-board))
		(should= true (draw? @draw-board))
		(should= false (draw? @o-win-board)))
	(it "gets available moves"
		(should= [0 1 2] (available-moves @moves-board)))
	(it "checks move for validity"
		(should= true (valid-move? @empty-board 0)))
	(it "determines the player who won"
		(should= "x" (game-winner @x-win-board))
		(should= "o" (game-winner @o-win-board))
		(should= nil (game-winner @draw-board)))
)