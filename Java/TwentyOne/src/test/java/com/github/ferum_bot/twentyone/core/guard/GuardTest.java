package com.github.ferum_bot.twentyone.core.guard;

import com.github.ferum_bot.twentyone.core.guard.impl.GameParametersGuard;
import com.github.ferum_bot.twentyone.models.game.GameParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.ferum_bot.twentyone.core.guard.GuardResult.FAILURE;
import static com.github.ferum_bot.twentyone.core.guard.GuardResult.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

class GuardTest {

    private Guard<GameParameters> guard;

    @BeforeEach
    public void onStart() {
        guard = new GameParametersGuard();
    }

    @AfterEach
    public void onFinish() {
        guard = null;
    }

    @Test
    public void ObtainValue_ValidState_SuccessResult() {
        var parameters1 = new GameParameters(3, 1);
        var parameters2 = new GameParameters(10, 0);
        var parameters3 = new GameParameters(40, 12);

        var actualResult1 = guard.obtainValue(parameters1);
        var actualResult2 = guard.obtainValue(parameters2);
        var actualResult3 = guard.obtainValue(parameters3);

        assertEquals(SUCCESS, actualResult1);
        assertEquals(SUCCESS, actualResult2);
        assertEquals(SUCCESS, actualResult3);
    }

    @Test
    public void ObtainValue_InvalidState_FailureResult() {
        var parameters1 = new GameParameters(0, 150);
        var parameters2 = new GameParameters(-1, -2);
        var parameters3 = new GameParameters(80, 30);

        var actualResult1 = guard.obtainValue(parameters1);
        var actualResult2 = guard.obtainValue(parameters2);
        var actualResult3 = guard.obtainValue(parameters3);

        assertEquals(FAILURE, actualResult1);
        assertEquals(FAILURE, actualResult2);
        assertEquals(FAILURE, actualResult3);
    }
}