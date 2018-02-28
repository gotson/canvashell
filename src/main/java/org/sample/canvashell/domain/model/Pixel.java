package org.sample.canvashell.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pixel {
    private final char color;

    public Pixel(Pixel copy){
        this.color = copy.color;
    }

    public Pixel duplicate(){
        return new Pixel(this);
    }
}
