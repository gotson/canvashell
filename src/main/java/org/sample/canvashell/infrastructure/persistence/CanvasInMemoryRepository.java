package org.sample.canvashell.infrastructure.persistence;

import lombok.Getter;
import lombok.Setter;
import org.sample.canvashell.domain.model.Canvas;
import org.springframework.stereotype.Component;

@Component
public class CanvasInMemoryRepository {
    @Getter
    @Setter
    private Canvas canvas = null;

    public void resetCanvas() {
        canvas = null;
    }
}
