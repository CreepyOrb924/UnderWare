package io.github.underware.config.gson;

import java.util.List;

public interface JsonStreamWriter<T> {

    void writeJsonStream(List<T> list);

}
