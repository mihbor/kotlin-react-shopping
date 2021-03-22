package shopping.redux

import kotlinx.browser.window
import kotlinx.coroutines.launch
import react.redux.useSelector
import redux.RAction
import redux.WrapperAction
import shopping.API
import shopping.model.Collection
import shopping.model.DirectoryState
import shopping.model.Section
import shopping.model.State
import shopping.scope

interface DirectoryEvent: RAction

class CollectionsFetchStarted: DirectoryEvent{
  override fun toString() = this::class.simpleName!!
}

data class CollectionsFetchSucceeded(val collections: List<Collection>): DirectoryEvent

data class CollectionsFetchFailed(val exception: Exception): DirectoryEvent

data class PopulateSections(val sections: List<Section>): RAction

fun directoryHandler(state: DirectoryState = DirectoryState(), action: RAction) = when (action) {
  is PopulateSections -> DirectoryState(action.sections, state.collections)
  is CollectionsFetchStarted -> DirectoryState(state.sections, state.collections, true)
  is CollectionsFetchSucceeded -> DirectoryState(state.sections, action.collections.map { it.routeName to it }.toMap(), false)
  is CollectionsFetchFailed -> DirectoryState(state.sections, state.collections, false)
  else -> state
}

fun fetchCollections(dispatch: (DirectoryEvent) -> WrapperAction) {
  scope.launch {
    dispatch(CollectionsFetchStarted())
    try {
      dispatch(CollectionsFetchSucceeded(API.getCollections()))
    } catch (e: Exception) {
      dispatch(CollectionsFetchFailed(e))
      window.alert(e.message!!)
    }
  }
}

fun getSections() = useSelector<State, List<Section>> { it.directory.sections }

fun getCollections() = useSelector<State, Map<String, Collection>> { it.directory.collections }

fun getCollection(name: String) = useSelector<State, Collection?> { it.directory.collections[name] }

fun collectionsFetching() = useSelector<State, Boolean> { it.directory.collectionsFetching }

fun collectionsPresent() = useSelector<State, Boolean> { !it.directory.collections.isNullOrEmpty() }
