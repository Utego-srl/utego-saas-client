package com.utego.saasapiclient.data.startsca;

import java.util.Objects;

public class SCAKindPictureLinkChallenge implements SCAKind {
    public String picture;

    public SCAKindPictureLinkChallenge(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCAKindPictureLinkChallenge that = (SCAKindPictureLinkChallenge) o;
        return picture.equals(that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(picture);
    }
}
