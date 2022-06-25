package com.diploma.chessing.model;

import com.diploma.chessing.enumeration.GameType;
import com.diploma.chessing.enumeration.Piece;
import com.diploma.chessing.enumeration.Square;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    private Long id;
    private Long whiteID;
    private Long blackID;
    private GameType gameType;
    private boolean hasStarted;
    private boolean hasFinished;
    private boolean hasWinner;
    private boolean isWhiteToMove;
    private LocalTime whiteTimeLeft;
    private LocalTime blackTimeLeft;
    private Long winnerID;
    @Type( type = "json" )
    private Map<Square, Piece> position;

    public Game(Long whiteID, Long blackID) {
        this.whiteID = whiteID;
        this.blackID = blackID;
        this.gameType = GameType.BLITZ;
        this.hasStarted = true;
        this.hasFinished = false;
        this.hasWinner = false;
        this.isWhiteToMove = true;
        this.whiteTimeLeft = LocalTime.of(0,10);
        this.blackTimeLeft = LocalTime.of(0,10);
        this.position = new HashMap<>() {{
            put(Square.a2, Piece.PAWN_WHITE);
            put(Square.b2, Piece.PAWN_WHITE);
            put(Square.c2, Piece.PAWN_WHITE);
            put(Square.d2, Piece.PAWN_WHITE);
            put(Square.e2, Piece.PAWN_WHITE);
            put(Square.f2, Piece.PAWN_WHITE);
            put(Square.g2, Piece.PAWN_WHITE);
            put(Square.h2, Piece.PAWN_WHITE);

            put(Square.a1, Piece.ROOK_WHITE);
            put(Square.b1, Piece.KNIGHT_WHITE);
            put(Square.c1, Piece.BISHOP_WHITE);
            put(Square.d1, Piece.QUEEN_WHITE);
            put(Square.e1, Piece.KING_WHITE);
            put(Square.f1, Piece.BISHOP_WHITE);
            put(Square.g1, Piece.KNIGHT_WHITE);
            put(Square.h1, Piece.ROOK_WHITE);

            put(Square.a7, Piece.PAWN_WHITE);
            put(Square.b7, Piece.PAWN_WHITE);
            put(Square.c7, Piece.PAWN_WHITE);
            put(Square.d7, Piece.PAWN_WHITE);
            put(Square.e7, Piece.PAWN_WHITE);
            put(Square.f7, Piece.PAWN_WHITE);
            put(Square.g7, Piece.PAWN_WHITE);
            put(Square.h7, Piece.PAWN_WHITE);

            put(Square.a8, Piece.ROOK_BLACK);
            put(Square.b8, Piece.KNIGHT_BLACK);
            put(Square.c8, Piece.BISHOP_BLACK);
            put(Square.d8, Piece.QUEEN_BLACK);
            put(Square.e8, Piece.KING_BLACK);
            put(Square.f8, Piece.BISHOP_BLACK);
            put(Square.g8, Piece.KNIGHT_BLACK);
            put(Square.h8, Piece.ROOK_BLACK);
        }};
    }
}
