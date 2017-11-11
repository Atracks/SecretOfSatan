'use strict';

var workerController = function($scope, $http, workerService, taskService) {
  $scope.project = {};
  $scope.editMode = false;
  getWorkersList();

  $scope.addNewWorker = function(newWorker) {
    resetError();
    workerService.addWorker(newWorker).success(function () {
      getWorkersList();
      $scope.project.description = '';
    }).error(function () {setError("Ошибка при добавлении работника");});
  };

  $scope.updateWorker = function(worker) {
    resetError();
    workerService.saveWorker(worker).success(function () {
      //getWorkersList();
      //$scope.worker.description = '';
      $scope.editMode = false;
    }).error(function () {setError("Ошибка при сохранении проекта");});
  };

  $scope.editProject = function(project) {
    resetError();
    $scope.project = project;
    $scope.editMode = true;
  };

  $scope.checkProjectForTasks = function (id) {
    resetError();
    projectService.setProjectIdForDelete(id);
    taskService.getTasksByProject(id).success(function (tasks) {
      if(tasks.length != 0) {window.location.replace('#/prjTasks');}
      else {deleteProject(id);}
    }).error(function () {setError("Ошибка при удалении проекта")});
  };

  function deleteProject(id) {
    resetError();
    projectService.deleteProject(id).success(function () {
      getProjectsList();
      $scope.project.description = '';
      $scope.editMode = false;
    }).error(function () {setError("Ошибка при удалении проекта");});
  };

  function getProjectsList() {
    projectService.getProjects().success(function (projects) {
      $scope.projects = projects;
    }).error(function () {setError("Ошибка получения списка проектов");});
  }

  function resetError() {
    $scope.error = false;
    $scope.errorMessage = '';
  };

  function setError (message) {
    $scope.error = true;
    $scope.errorMessage = message;
  }
};
