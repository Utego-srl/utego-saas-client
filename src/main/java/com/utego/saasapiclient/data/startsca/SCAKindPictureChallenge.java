package com.utego.saasapiclient.data.startsca;

import java.nio.ByteBuffer;
import java.util.Objects;

public class SCAKindPictureChallenge implements SCAKind {
    public ByteBuffer picture;

    public SCAKindPictureChallenge(ByteBuffer picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCAKindPictureChallenge that = (SCAKindPictureChallenge) o;
        return picture.equals(that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(picture);
    }
}
