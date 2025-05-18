package com.mongoDb;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConnectMongo {
    public static void main(String[] args) {
        MongoDatabase mongoDatabase = getMondoDb();
        MongoCollection<Document> collection;
        try{
            collection = mongoDatabase.getCollection("vineetCollection");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        insertDocument(collection);

        iterateDocuments(collection);

        collection.updateOne(Filters.eq("title", "MongoDB"), Updates.set("likes", 150));

        iterateDocuments(collection);
    }

    private static void iterateDocuments(MongoCollection<Document> collection) {
        FindIterable<Document> documents =  collection.find();
        Iterator iterator = documents.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static void insertDocument(MongoCollection<Document> collection) {
        Document document1 = new Document("title", "MongoDB")
                .append("description", "database")
                .append("likes", 100);
        Document document2 = new Document("title", "MongoDB1")
                .append("description", "database1")
                .append("likes", 200)
                .append("by", "Vineet");
        List<Document> list = new ArrayList<>();
        list.add(document1);
        list.add(document2);
        collection.insertMany(list);
    }

    private static MongoDatabase getMondoDb() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoCredential.createCredential("vineet" , "vineetDb" , "password".toCharArray());
        return mongoClient.getDatabase("vineetDb");
    }
}
