(function() {
    
    var SubjectRatingController = function ($scope, $route,$http) {
        $scope.hasCourse = false;
        $scope.disableButton = false;
        $scope.rated = false;
        $scope.hasNotSelectedSubject = true;
        $scope.subjectRatingList = [];
        $scope.courseList = [];
        var subjectRatingCount = 0;
        var ratedSubjects = {};

        $http.get("http://localhost:8081/subject/list")
            .success(function(response) {
                //console.log(response.subjects);
                $scope.subjectList = response.subjects;
                createSubjectRatingSubjects();
            });

        $scope.rateAnotherSubject = rateAnotherSubject;
        $scope.showAdviseCourse = showAdviseCourse;

        function rateAnotherSubject(){
            saveSubjectRating();
            disableFieldSet(subjectRatingCount);

            subjectRatingCount += 1;
            createSubjectRatingSubjects(subjectRatingCount);

            if(subjectRatingCount >= $scope.subjectList.length - 1){
                $scope.disableButton = true;
            }
        }
        function showAdviseCourse(){
            saveSubjectRating();
            //console.log("ratedSubjects : " + ratedSubjects);
            $http({
                method: "GET",
                url : "http://localhost:8081/course/advice",
                params : ratedSubjects
            })
                .success(function(response) {
                    //console.log(response);
                    $scope.hasCourse = true;
                    //$scope.courseList = response.suggestions;
                    $scope.courseList = response.courseDTOList;
                });
        }
        function saveSubjectRating(){
            var select = document.getElementById("subject_"+ subjectRatingCount)
            var subject = select.options[select.selectedIndex].value;
            var rating = document.querySelector('input[name="radioName_'+subjectRatingCount+'"]:checked').value;
            ratedSubjects[subject] = rating;

        }
        function disableFieldSet(index){
            document.getElementById("rating_"+index).disabled = true;
        }
        function createSubjectRatingSubjects(){
            var element = document.getElementById("content");

            var hr = document.createElement("hr");
            var div = document.createElement("div");
            div.className = 'row';

            var htmlStr = '<fieldset id="rating_'+subjectRatingCount+'"><div class="col-md-4">' +
                '<select id="subject_'+subjectRatingCount+'" class="form-control">' ;

            for(var i = 0; i < $scope.subjectList.length; i++  ){
                if(!ratedSubjects[$scope.subjectList[i].code]){
                    htmlStr += '<option value="'+$scope.subjectList[i].code+'">'+$scope.subjectList[i].name+'</option>' ;
                }
            }
            htmlStr +=      '</select></div>'+
                '<div class="col-md-8" >' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="0" checked>0' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="1">1' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="2">2' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="3">3' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="4">4' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="5">5' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="6">6' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="7">7' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="8">8' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="9">9' +
                '<input type="radio" name="radioName_'+subjectRatingCount+'" value="10">10' +

                '</div>' +
                '</fieldset>';
            div.innerHTML = htmlStr;
            element.appendChild(div);
            element.appendChild(hr);
        }

	};

    SubjectRatingController.$inject = ['$scope','$route','$http'];

    angular.module('ruleApp.subjectRatingApp')
      .controller('SubjectRatingController', SubjectRatingController);

}());
