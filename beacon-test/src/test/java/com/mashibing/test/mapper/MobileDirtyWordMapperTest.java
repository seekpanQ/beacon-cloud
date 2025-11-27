package com.mashibing.test.mapper;

import com.mashibing.test.client.CacheClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MobileDirtyWordMapperTest {
    @Autowired
    private CacheClient cacheClient;

    @Autowired
    private MobileDirtyWordMapper mapper;

    @Test
    public void findAll() {

        List<String> dirtyWords = mapper.findDirtyWord();
        cacheClient.saddStr("dirty_word", dirtyWords.toArray(new String[]{}));

    }
}
