#Author: Imran Abbas Satti
#
# As I am using BDD approach this is my feature file
# Corresponding steps file can be found at "/AutomationBot/src/test/java/steps_binder/CommentSteps.java"
# 

@comment_module @regression
Feature: Testing commnet module functionality
	As a member of QA team
  I want to test commnet module
  So that I can assure quality on rest api layer

  @api @smoke @get @positive @health @automated
  Scenario Outline: Validate health for users, post and comments end points
    Given User has a valid end point "<endpoint>"
    When User sends get request to given end point
    Then verify the status line and tontent type should be "<status_line>" and "<content_type>"
    
    Examples: 
      |  endpoint 	|   status_line   |           content_type          |
      |   /users  	| HTTP/1.1 200 OK | application/json; charset=utf-8 |
			|   /posts    | HTTP/1.1 200 OK | application/json; charset=utf-8 |
			|  /comments  | HTTP/1.1 200 OK | application/json; charset=utf-8 |

	@api @sanity @get @positive @schema @automated
  Scenario Outline: Validate the json schema for users, posts and comments end points
    Given User has a valid end point "<endpoint>"
    When User sends get request to given end point
    Then verify json schema of given endpoint should be "<json_schema_source>"
    Examples: 
      |  endpoint 	|   					json_schema_source  				     |           
      |  /users/1  	| testData/json/users-endpoint-schema.json     | 
      |  /posts/1   | testData/json/posts-endpoint-schema.json     | 
      | /comments/1 | testData/json/comments-endpoint-schema.json  | 
    
    
	@api @integartion @end2end @positive @automated
  Scenario Outline: Validate email formatting in comment section
    Given With a valid user with user name "<name>"
    And Filter and store user id using end point "/users" on the basis of user name
    And Filter and store all of the post ids using end point "/posts" on the basis of user id
    When Filter and store all of email values from comments using "/posts/{post_id}/comments" on the basis of post ids
    Then verify all of the email values should be in valid format
    Examples: 
      | name     |
      | Delphine |
      | Kamren   |
    
