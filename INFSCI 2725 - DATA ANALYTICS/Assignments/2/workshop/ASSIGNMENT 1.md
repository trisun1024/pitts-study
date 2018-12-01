##ASSIGNMENT 1 - Storage and processing of data

#####By Jing Pang, Tian Xue, Chuqian Ma, Jiaxiang Leng

To fulfill the request, we choose to use MongoDB as database storage for the dataset which consists of 10 million ratings and 100,000 tag applications applied to 10,000 movies by 72,000 users. We construct a local environment for a MongoDB database to store our data. We set up a new database named mydb as our initial database. Then we use Java language as a pipeline to load the primitive data from three separate files into the MongoDB database. The whole process could review from java file named as DBMoviesBuilder, DBTagsBuilder and DBRatingsBuilder. We answered four questions as requested. We firstly solve each question answer by using MongoDB's shell language. Then we have translated this method into Java language. The DBQueryOne to DBQueryFour will point to each problem separately.
The following screenshots show that how the data load into the MongoDB database by using Java code.

![Screen Shot 2018-10-06 at 22.22.24](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.22.24.png)

![Screen Shot 2018-10-06 at 22.22.34](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.22.34.png)

![Screen Shot 2018-10-06 at 22.22.40](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.22.40.png)/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.23.06.png

1. Write a query that finds average rating of each movie.

   To answer this question, we write a simple MongoDB shell query to help us figure out the correct solution. Then we translate this theory into a Java language to fit with this shell query.

```
db.ratings.aggregate( [ 
    { $group: { _id: "$MovieID", avgRating: { $avg: "$Rating" } } }, { $sort : { _id : 1 } } 
]).pretty()
```

![Screen Shot 2018-10-06 at 22.23](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.23.png)

![Screen Shot 2018-10-06 at 21.02.57](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 21.02.57.png)

2. Write a query that finds users who are similar to a given user (target user), the id of the target user is an input parameter. Users are similar to the target user if they rate the same movies.

   First of all, we use a query to find out the total number of users, which is 71509.

```
db.ratings.aggregate([
     {
       $group:
         {
			_id: "$MovieID",
           max: { $max: "$UserID" }
         }
     }
])
```

Secondly, we have to create a temporary table that contains the information between users and the statistical result of rating movies. The MongoDB's bucket function is using in this process, and we used preious find of total user number as a bucket parameter setting.

```
db.ratings.aggregate([
    { $group: {
        _id: "$UserID",
        count: { $sum: 1 },
        Movies: {$push: "$MovieID"}
    }}
])
```

In the end, we find this question's answer by using MongoDB lookup function through the temporary table. The solution to how to successfully getting the correct result is shown in the following query. The Java file will show that how do we obtain this one by converting the idea from query to Java language. 

```
db.tempuser.aggregate([
   {
      $lookup:
         {
           from: "tempuser",
           let: { numberOfMovies: "$numberOfMovies", movies: "$Movies" },
           pipeline: [
              { $match:
                 { $expr:
                    { $and:
                       [
                         { $eq: [ "$Movies",  "$$movies" ] },
                         { $eq: [ "$numberOfMovies",  "$$numberOfMovies" ] }
                       ]
                    }
                 }
              }
           ],
           as: "moviematch"
         }
    },
    { $match: { "_id": { $eq: 1} }},
    { $project: { moviematch: 1, _id: 0}}
]).pretty()
```

Because the dataset is extremely large, we only test our proccess in our test database. The screenshots of whole process results are shown in the following.

We build a simple database to test our query

```
db.test.insert([
   { "_id" : 1, "MovieID" : 1, "UserID" : 1},
   { "_id" : 2, "MovieID" : 1, "UserID" : 2},
   { "_id" : 3, "MovieID" : 1, "UserID" : 3},
   { "_id" : 4, "MovieID" : 1, "UserID" : 4},
   { "_id" : 5, "MovieID" : 1, "UserID" : 5},
   { "_id" : 6, "MovieID" : 1, "UserID" : 6},
   { "_id" : 7, "MovieID" : 1, "UserID" : 7},
   { "_id" : 8, "MovieID" : 1, "UserID" : 8},
   { "_id" : 9, "MovieID" : 1, "UserID" : 9},
   { "_id" : 10, "MovieID" : 2, "UserID" : 1},
   { "_id" : 11, "MovieID" : 2, "UserID" : 2},
   { "_id" : 12, "MovieID" : 1, "UserID" : 4},
   { "_id" : 13, "MovieID" : 1, "UserID" : 11},
   { "_id" : 14, "MovieID" : 1, "UserID" : 12},
   { "_id" : 15, "MovieID" : 1, "UserID" : 13},
   { "_id" : 16, "MovieID" : 1, "UserID" : 10},
   { "_id" : 17, "MovieID" : 1, "UserID" : 14},
   { "_id" : 18, "MovieID" : 1, "UserID" : 15},
   { "_id" : 19, "MovieID" : 1, "UserID" : 16},
   { "_id" : 20, "MovieID" : 1, "UserID" : 17},
])
```

By using this database, it should result a return tuple matching with input target UserID 1.

![Screen Shot 2018-10-06 at 21.48.17](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 21.48.17.png)

![Screen Shot 2018-10-06 at 22.23.54](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.23.54.png)

3. Write a query that finds to number of movies in each genre.

To answer this question, we write a simple MongoDB shell query to help us figure out the correct solution. Then we translate this theory into a Java language to fit with this shell query.

```
db.movies.aggregate([
    { $project : { genre : { $split: ["$Genres", "|"] }, MovieID: 1} },
    { $unwind : "$genre" },
    { $group: { _id: "$genre", numberOfMovies: { $sum: 1 } } }
]).pretty()
```

![Screen Shot 2018-10-06 at 22.24.09](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.24.09.png)

![Screen Shot 2018-10-06 at 21.03.15](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 21.03.15.png)

4. Write 3 different queries of your choice to demonstrate that your data storage is working.

   4.1. Show each movie’s information and the tags.

```
1>. Show each movie’s information and the tags.
db.movies.aggregate([
   {
      $lookup:
         {
           from: "tags",
           let: { movieid: "$MovieID" },
           pipeline: [
              { $match:
                 { $expr:
                    { $and:
                       [
                         { $eq: [ "$MovieID",  "$$movieid" ] }
                       ]
                    }
                 }
              },
              { $project: { _id:0,Tag:1 } }
           ],
           as: "movietags"
         }
    }
]).pretty()
```

![Screen Shot 2018-10-06 at 22.24.22](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.24.22.png)

![WechatIMG1](/Users/pangjing/Desktop/WechatIMG1.jpeg)

4.2. Show all movie information evaluated by person with userid of 1024.

```
2>. Show all movie information evaluated by person with userid of 1024.
db.ratings.aggregate([
   {
      $lookup:
         {
           from: "ratings",
           let: { movieid: "$MovieID"},
           pipeline: [
              { $match:
                    { $and:
                       [
                         { $eq: [ "$MovieID",  "$$movieid" ] },
                         { $eq: [ "$UserID", 1024 ] }
                       ]
                 }
              },
              { $project: { MovieID: 0, _id: 0 } }
           ],
           as: "moviematch"
         }
    },
    { $group : { _id : "$MovieID" } }
]).pretty()
```

![Screen Shot 2018-10-06 at 22.24.59](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.24.59.png)

![IMG_1568](/Users/pangjing/Desktop/IMG_1568.PNG)

4.3. Show all movies with larger-than-four average rating.

```
3>. Show all movies with larger-than-four average rating.
db.ratings.aggregate([
{$group:{ _id:"$MovieID",AverageRating:{$avg:"$Rating"}}}, 
{$match:{AverageRating:{$gte:4}}},
{$sort:{AverageRating:1}}
]).pretty()
```

![Screen Shot 2018-10-06 at 22.25.06](/Users/pangjing/Desktop/Screen Shot 2018-10-06 at 22.25.06.png)

![WechatIMG4](/Users/pangjing/Desktop/WechatIMG4.jpeg)