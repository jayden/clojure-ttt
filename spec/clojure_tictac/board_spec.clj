(ns clojure_tictac.board-spec
  (:require [speclj.core :refer :all]
            [clojure_tictac.core :refer :all]
            [clojure_tictac.board :refer :all]))

(describe "ttt board"

	(with empty-board [nil nil nil nil nil nil nil nil nil])
	(with empty-small-board [nil nil nil nil])
	(with x-board ["x" nil nil nil nil nil nil nil nil])
	(with test-board [0 1 2 3 4 5 6 7 8])
	(with rows '((0 1 2) (3 4 5) (6 7 8)))
	(with cols '((0 3 6) (1 4 7) (2 5 8)))
	(with left-diagonal '(0 4 8))
	(with right-diagonal '(2 4 6))
	(with has-nil-board [nil nil nil "x" "o" "o" "x" "x" "o"])

	(it "makes an empty board"
		(should= @empty-board 
						(make-board 9))
		(should= @empty-small-board
						(make-board 4)))

	(it "fills spaces in the board"
		(should= @x-board
						(fill-space @empty-board 0 "x")))

	(it "returns board size"
		(should= 3 
						(board-size @empty-board)))

	(it "returns rows of board"
		(should= @rows 
						(get-rows @test-board)))

	(it "returns columns of board"
		(should= @cols 
						(get-cols @test-board)))

	(it "returns left diagonal of board"
		(should= @left-diagonal 
						(left-diag @test-board)))

	(it "returns right diagonal of board"
		(should= @right-diagonal
						(right-diag @test-board)))

	(it "returns both diagonals of board"
		(should= (list @left-diagonal @right-diagonal)
							(get-diags @test-board)))

	(it "checks for full board"
		(should-not (full? @empty-board))
		(should (full? @test-board)))

	(it "puts changes nil to indices"
		(should= '(0 1 2 3 4 5 6 7 8)
							(board-indices @empty-board)))
	(it "returns empty spaces"
		(should= '(0 1 2)
							(empty-spaces @has-nil-board)))
)