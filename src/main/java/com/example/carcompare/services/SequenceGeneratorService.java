package com.example.carcompare.services;

import com.example.carcompare.model.DatabaseSequence;
import com.example.carcompare.model.SequenceGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    @Autowired
    MongoTemplate mongoTemplate;

    public int getNextSequenceId(SequenceGen object) {
        Query query = new Query(Criteria.where("_id").is(object.getSequenceName()));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        DatabaseSequence seqId =
                mongoTemplate.findAndModify(query, update, options, DatabaseSequence.class);

        if (seqId == null) {
            mongoTemplate.insert(new DatabaseSequence(object.getSequenceName(),1));
            return 1;
        }
        return seqId.getSeq();
    }
}
